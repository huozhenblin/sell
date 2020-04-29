package com.huo.service;

import com.huo.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 16:52 2020/4/28
 */
public interface OrderService {
    //    创建订单
    OrderDTO create(OrderDTO oderDTO);

    //    查询单个订单
    OrderDTO findOne(String orderId);

    //    查询订单列表
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    //    取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //    完结订单
    OrderDTO finsh(OrderDTO orderDTO);

    //支付订单
    OrderDTO paind(OrderDTO orderDTO);


}
