package com.huo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: Huo
 * @Description: 商品实体类
 * @Date: Create in 10:29 2020/4/21
 */
@Entity
@Data
@DynamicUpdate  //动态更新
@NoArgsConstructor
public class ProductCategory {
    //类目id
    @Id
    @GeneratedValue
    private Integer categoryId;
    //类目名字
    private String categoryName;
    //类目类型
    private Integer categoryType;
    //创建时间
    private Date createTime;
    //    更新时间
    private Date updateTime;

}
