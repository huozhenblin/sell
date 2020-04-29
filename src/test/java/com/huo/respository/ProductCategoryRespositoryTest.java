package com.huo.respository;

import com.huo.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 10:41 2020/4/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRespositoryTest {
    @Autowired
    private ProductCategoryRespository productCategoryRespository;

    //测试查询一条数据
    @Test
    public void findOneTest() {
        ProductCategory one = productCategoryRespository.findOne(1);
        System.out.println(one);
    }

    //    测试新增一条数据
    @Test
    @Transactional
    public void addOneTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("人妖最爱");
        productCategory.setCategoryType(4);
        ProductCategory save = productCategoryRespository.save(productCategory);
        Assert.assertNotNull(save);
//        Assert.assertNotNull(save);

    }

    //    测试更新
    @Test
    public void updateOneTest() {
//        更新前先查出该条数据的数据
        ProductCategory one = productCategoryRespository.findOne(1);
//       改变值
        one.setCategoryType(11);
        productCategoryRespository.save(one);
    }
//    测试根据类型数组查询相应的类目使用 in的方式查询里面的内容
    @Test
    public void findByCategoryTypeIntest(){
        List<Integer> list = Arrays.asList(11,3);
        List<ProductCategory> byCategoryTypeIn = productCategoryRespository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, byCategoryTypeIn.size());
    }
}