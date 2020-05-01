package com.huo.enums;

import lombok.Getter;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 17:43 2020/4/28
 */
@Getter
public enum  ResultEnmu {
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品不不足"),
    PRODUCT_STOCK_ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_REEOR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"取消订单为空"),
    CART_EMPTY(18,"购物车为空"),
    ORDER_OWNER_REEOR(19,"该订单不属于当前用户");

    private Integer code;
    private String message;

    ResultEnmu(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
