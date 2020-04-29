package com.huo.service.impl;

import com.huo.dto.CartDTO;
import com.huo.dto.OrderDTO;
import com.huo.entity.OrderDetail;
import com.huo.entity.OrderMaster;
import com.huo.entity.ProductInfo;
import com.huo.enums.ResultEnmu;
import com.huo.respository.OrderDetailRepository;
import com.huo.respository.OrderMasterRepository;
import com.huo.sellException.SellException;
import com.huo.service.OrderService;
import com.huo.service.ProductService;
import com.huo.until.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

//        查询商品（数量，价格）
        for (OrderDetail orderDetail : oderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnmu.PRODUCT_NOT_EXIST);
            }
            //        计算订单总价
            orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
//            写入订单详情表
            orderDetail.setOrderId(KeyUtil.gunUniqueKey());
            orderDetail.setDetailId(KeyUtil.gunUniqueKey());
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }
//        写入ordermast表
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(oderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);
//        扣库存
        List<CartDTO> cartDTOList = new ArrayList<>();
        List<CartDTO> list = oderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(list);
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finsh(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paind(OrderDTO orderDTO) {
        return null;
    }
}
