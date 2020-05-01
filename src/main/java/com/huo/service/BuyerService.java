package com.huo.service;

import com.huo.dto.OrderDTO;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 17:14 2020/5/1
 */
public interface BuyerService {
    /*查询一个订单*/
    OrderDTO findOrderOne(String openid, String orderId);
    OrderDTO cancelOrderOne(String openid, String orderId);
}
