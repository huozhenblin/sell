package com.huo.service.impl;

import com.huo.converter.OrderMasterToOrderDTO;
import com.huo.dto.CartDTO;
import com.huo.dto.OrderDTO;
import com.huo.entity.OrderDetail;
import com.huo.entity.OrderMaster;
import com.huo.entity.ProductInfo;
import com.huo.enums.OrderStatusEnmu;
import com.huo.enums.PayStatusEnum;
import com.huo.enums.ResultEnmu;
import com.huo.respository.OrderDetailRepository;
import com.huo.respository.OrderMasterRepository;
import com.huo.sellException.SellException;
import com.huo.service.OrderService;
import com.huo.service.ProductService;
import com.huo.until.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Huo
 * @Description:商品订单处理
 * @Date: Create in 17:24 2020/4/28
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO oderDTO) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.gunUniqueKey();

//        查询商品（数量，价格）
        for (OrderDetail orderDetail : oderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnmu.PRODUCT_NOT_EXIST);
            }
            //        计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
//            写入订单详情表
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.gunUniqueKey());
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }
//        写入ordermast表
        OrderMaster orderMaster = new OrderMaster();
        oderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(oderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);
//        扣库存
        List<CartDTO> cartDTOList = new ArrayList<>();
        List<CartDTO> list = oderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(list);
        return oderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster master = orderMasterRepository.findOne(orderId);
        if (master==null){
            throw new SellException(ResultEnmu.PRODUCT_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (orderDetailList == null){
            throw new SellException(ResultEnmu.PRODUCT_STOCK_ORDER_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId,pageable);
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTO.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster =new OrderMaster();
//        1.判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnmu.NEW.getCode())){
            log.error("订单状态不正确 orderid={},orderstatus",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnmu.ORDER_STATUS_REEOR);
        }
//        2.修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnmu.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult ==null){
            log.error("订单更新失败,ordermaster={}",orderMaster);
            throw new SellException(ResultEnmu.ORDER_UPDATE_FAIL);
        }
//        3.返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("取消订单中无商品详情orderdto={}",orderDTO);
            throw new SellException(ResultEnmu.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> list = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(list);
//        如果以支付，需退款
        if (orderDTO.getOrderStatus().equals(PayStatusEnum.SUCCESS)){
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finsh(OrderDTO orderDTO) {
        /*查看订单状态*/
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnmu.NEW.getCode())){
            log.error("订单状态不正确 orderid={},orderstatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnmu.ORDER_STATUS_REEOR);
        }
        /*修改订单状态*/
        orderDTO.setOrderStatus(OrderStatusEnmu.FINISH.getCode());
        OrderMaster orderMaster =new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult ==null){
            log.error("【完结订单】订单更新失败,ordermaster={}",orderMaster);
            throw new SellException(ResultEnmu.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paind(OrderDTO orderDTO) {
        /*判断订单状态*/
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnmu.NEW.getCode())){
            log.error("【订单支付完成】订单状态不正确 orderid={},orderstatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnmu.ORDER_STATUS_REEOR);
        }
        /*判断支付状态*/
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】订单状态不正确 orderid={},orderstatus={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnmu.ORDER_STATUS_REEOR);
        }
        /*修改支付状态*/
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster =new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult ==null){
            log.error("【订单支付完成】更新失败,ordermaster={}",orderMaster);
            throw new SellException(ResultEnmu.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
