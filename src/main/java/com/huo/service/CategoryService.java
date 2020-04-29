package com.huo.service;

import com.huo.entity.ProductCategory;

import java.util.List;

/**
 * @Author: Huo
 * @Description:商品类目接口层
 * @Date: Create in 9:19 2020/4/23
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    //查找所有产品
    List<ProductCategory> findAll();
//买家端根据类型查找所有产品
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
//保存商品列表
    ProductCategory save(ProductCategory productCategory);

}
