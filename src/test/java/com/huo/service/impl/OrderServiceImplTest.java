package com.huo.service.impl;

import com.huo.dto.OrderDTO;
import com.huo.entity.OrderDetail;
import com.huo.enums.OrderStatusEnmu;
import com.huo.enums.PayStatusEnum;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 16:10 2020/4/29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {
    private static final String BUYER_OPID = "110110";
    private static final String ORDER_ID = "1588153372365960500";
    @Autowired
    OrderServiceImpl orderService;
    @Test
    public void create() {
//        订单基本信息
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("霍少");
        orderDTO.setBuyerAddress("周口本地");
        orderDTO.setBuyerPhone("188888888");
        orderDTO.setBuyerOpenid(BUYER_OPID);
//        购物车信息
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123");
        orderDetail.setProductQuantity(2);
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("124");
        orderDetail1.setProductQuantity(2);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail1);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO orderDTO1 = orderService.create(orderDTO);

        log.info("[创建订单]orderDTO1={} ",orderDTO1);
        Assert.assertNotNull(orderDTO1);

    }

    @Test
    public void findOne() {
        OrderDTO one = orderService.findOne(ORDER_ID);
        log.info("[查询单个订单] result={}",one);
        Assert.assertEquals(ORDER_ID, one.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest =new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPID, pageRequest);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnmu.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finsh() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finsh(orderDTO);
        Assert.assertEquals(OrderStatusEnmu.FINISH.getCode(), result.getOrderStatus());
    }

    @Test
    public void paind() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paind(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());

    }
}