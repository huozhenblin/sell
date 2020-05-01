package com.huo.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 14:48 2020/4/30
 */
@Data
public class OrderForm {
    /*买家姓名*/
    @NotEmpty(message = "姓名必填")
    private String name;
    /*买家手机号*/
    @NotEmpty(message = "手机号必填")
    private String phone;
    /*买家地址*/
    @NotEmpty(message = "地址必填")
    private String address;
    /*openID*/
    @NotEmpty(message = "openid必填")
    private String openid;
    /*购物车*/
    @NotEmpty(message = "物品必填")
    private String items;
}
