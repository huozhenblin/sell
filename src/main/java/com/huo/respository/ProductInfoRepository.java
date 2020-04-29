package com.huo.respository;

import com.huo.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Huo
 * @Description:商品信息dao层
 * @Date: Create in 11:22 2020/4/23
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);


}
