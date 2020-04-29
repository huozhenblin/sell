package com.huo.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 18:18 2020/4/25
 */
@Getter
public enum OrderStatusEnmu {
    NEW(0,"新订单"),
    FINISH(1,"完结"),
    CANCEL(2,"取消订单");
   private Integer code;
   private String   msg;

    OrderStatusEnmu(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
