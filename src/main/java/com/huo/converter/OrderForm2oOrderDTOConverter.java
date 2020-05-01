package com.huo.converter;

import com.alibaba.fastjson.JSON;
import com.huo.dto.OrderDTO;
import com.huo.entity.OrderDetail;
import com.huo.enums.ResultEnmu;
import com.huo.form.OrderForm;
import com.huo.sellException.SellException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Huo
 * @Description:将请求表单数据转化为传输的DTO类
 * @Date: Create in 17:44 2020/4/30
 */
@Slf4j
public class OrderForm2oOrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {


        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        /*购物车*/
        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            orderDetailList = JSON.parseArray(orderForm.getItems(), OrderDetail.class);
        } catch (Exception e) {
            log.error("对象转换错误：string={}", orderForm.getItems());
            throw new SellException(ResultEnmu.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
