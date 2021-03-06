package com.huo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: Huo
 * @Description:产品信息实体类
 * @Date: Create in 10:14 2020/4/23
 */
@Entity
@Data
@NoArgsConstructor
public class ProductInfo {
    @Id
    private String productId;
//    名字
    private String productName;
//    单价
    private BigDecimal productPrice;
//    库存
    private Integer productStock;
//    描述
    private String productDescription;
//    小图
    private String productIcon;
//  类目编号
    private Integer categoryType;
//    商品状态'商品状态，0正常1下架'
    private Integer productStatus;

}
