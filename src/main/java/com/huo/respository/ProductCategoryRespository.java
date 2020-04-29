package com.huo.respository;

import com.huo.entity.ProductCategory;
import com.huo.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 10:38 2020/4/21
 */
public interface ProductCategoryRespository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    void save(ProductInfo productInfo);
}
