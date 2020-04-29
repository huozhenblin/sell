package com.huo.respository;

import com.huo.entity.ProductCategory;
import com.huo.entity.ProductInfo;
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
 * @Description:商品信息测试类
 * @Date: Create in 11:38 2020/4/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository repository;

    @Test
    public void findOne() {
        ProductInfo one = repository.findOne("122");

        System.out.println(one);

    }

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("125");
        productInfo.setProductName("可乐");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("冰冰的可乐");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(0);
        ProductInfo save = repository.save(productInfo);
        Assert.assertNotNull(save);

    }

    @Test
    public void findAllTest() {
        List<ProductInfo> list = repository.findAll();

    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = repository.findByProductStatus(0);
//        Assert.assertNotNull(0, list.size());
        Assert.assertNotEquals(0, list.size());
    }
}