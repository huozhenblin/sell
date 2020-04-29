package com.huo.enums;

import lombok.Getter;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 18:23 2020/4/25
 */
@Getter
public enum PayStatusEnum {
   WAIT(0,"未支付"),
    SUCCESS(1,"支付成功");
    private Integer code;
    private String   msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
