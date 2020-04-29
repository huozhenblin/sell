package com.huo.service;

import com.huo.dto.CartDTO;
import com.huo.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: Huo
 * @Description:产品信息表
 * @Date: Create in 12:14 2020/4/23
 */
public interface ProductService {
    ProductInfo findOne(String productId);

    //    查询所有在架商品信息
    List<ProductInfo> findUpAll();

    //分页查询
    Page<ProductInfo> findAll(Pageable pageable);

    //    保存信息
    ProductInfo save(ProductInfo productInfo);

    //    加库存
    void increaseStock(List<CartDTO> cartDTOSList);

    //    减库存
    void decreaseStock(List<CartDTO> cartDTOSList);

}
