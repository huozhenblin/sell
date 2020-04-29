package com.huo.respository;

import com.huo.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 18:37 2020/4/25
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    //根据某人查询订单分页查询
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);


}
