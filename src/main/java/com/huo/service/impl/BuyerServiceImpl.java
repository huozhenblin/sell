package com.huo.service.impl;

import com.huo.dto.OrderDTO;
import com.huo.enums.ResultEnmu;
import com.huo.sellException.SellException;
import com.huo.service.BuyerService;
import com.huo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 17:18 2020/5/1
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null){
            log.error("取消订单，但订单为空");
            throw new SellException(ResultEnmu.ORDER_DETAIL_EMPTY);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("openid不一致。openid={}。orderdto={}", orderDTO.getBuyerOpenid(), openid);
            throw new SellException(ResultEnmu.ORDER_OWNER_REEOR);
        }
        return orderDTO;
    }
}
