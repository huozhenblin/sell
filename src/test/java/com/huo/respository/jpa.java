package com.huo.respository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Huo
 * @Description:jpa测试
 * @Date: Create in 19:00 2020/4/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class jpa {
    @Autowired
    private ProductCategoryRespository productCategoryRespository;



}
