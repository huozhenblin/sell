package com.huo.enums;

import lombok.Getter;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 12:21 2020/4/23
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架")

    ;
    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
