package com.huo.respository;

import com.huo.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 18:51 2020/4/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    OrderMasterRepository orderMasterRepository;

    private  final String OPENID = "110110";
//保存的方法
    @Test
    public void sava(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("212");
        orderMaster.setBuyerName("huodalao");
        orderMaster.setBuyerPhone("1232300000");
        orderMaster.setBuyerAddress("天津");
        orderMaster.setOrderAmount(new BigDecimal(300));
        orderMaster.setBuyerOpenid(OPENID);


        OrderMaster save = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(1, 3);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0, result.getTotalElements());
        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
    }
}