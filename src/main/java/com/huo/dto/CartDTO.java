package com.huo.dto;

import lombok.Data;

/**
 * @Author: Huo
 * @Description:购物车
 * @Date: Create in 21:09 2020/4/28
 */
@Data
public class CartDTO {
//    商品id
    private String productId;

//    数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
