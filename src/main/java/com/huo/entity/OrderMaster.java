package com.huo.entity;

import com.huo.enums.OrderStatusEnmu;
import com.huo.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: Huo
 * @Description: 订单实体类
 * @Date: Create in 18:12 2020/4/25
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    //    买家名字
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //卖家地址
    private String buyerAddress;
    //买家微信id
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态 默认状态未下单
    private Integer orderStatus = OrderStatusEnmu.NEW.getCode();
    //支付状态
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
// 订单内容

}
