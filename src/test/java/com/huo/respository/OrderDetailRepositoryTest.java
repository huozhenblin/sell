package com.huo.respository;

import com.huo.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 20:47 2020/4/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1120");
        orderDetail.setOrderId("1111111");
        orderDetail.setProductIcon("http://xxxxx");
        orderDetail.setProductId("123443");
        orderDetail.setProductName("黄焖鸡");
        orderDetail.setProductPrice(new BigDecimal(4.2));
        orderDetail.setProductQuantity(5);
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = orderDetailRepository.findByOrderId("1111112");
        Assert.assertNotEquals(0, result.size());

    }


}