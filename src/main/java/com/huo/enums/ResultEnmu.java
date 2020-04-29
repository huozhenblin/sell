package com.huo.enums;

import lombok.Getter;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 17:43 2020/4/28
 */
@Getter
public enum  ResultEnmu {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品不不足");

    private Integer code;
    private String message;

    ResultEnmu(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
