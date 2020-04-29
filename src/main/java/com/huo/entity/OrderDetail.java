package com.huo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: Huo
 * @Description:订单详情表
 * @Date: Create in 18:30 2020/4/25
 */
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;
    //    订单id
    private String orderId;
    //商品id
    private String productId;
    //商品名字
    private String productName;
    //商品单价
    private BigDecimal productPrice;
    //商品数量
    private Integer productQuantity;
    //小图
    private String productIcon;
}
