package com.huo.respository;


import com.huo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 18:42 2020/4/25
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
//    根据订单的订单ID查询此订单的商品详情

    List<OrderDetail> findByOrderId(String orderId);
}
