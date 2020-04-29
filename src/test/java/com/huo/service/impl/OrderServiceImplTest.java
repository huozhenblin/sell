package com.huo.service.impl;

import com.huo.dto.OrderDTO;
import com.huo.entity.OrderDetail;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        orderDetail.setProductId("122");
        orderDetail.setProductQuantity(2);

        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO orderDTO1 = orderService.create(orderDTO);

        log.info("[创建订单]orderDTO1={} ",orderDTO1);


    }

    @Test
    public void findOne() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finsh() {
    }

    @Test
    public void paind() {
    }
}