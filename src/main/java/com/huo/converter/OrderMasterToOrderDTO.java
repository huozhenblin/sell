package com.huo.converter;

import com.huo.dto.OrderDTO;
import com.huo.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 18:33 2020/4/29
 */
public class OrderMasterToOrderDTO {
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);

        return  orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> masterList){
        List<OrderDTO> orderDTOList1 = masterList.stream().
                map(e -> convert(e))
                .collect(Collectors.toList());
        return orderDTOList1;

    }


}
