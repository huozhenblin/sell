package com.huo.controller;

import com.huo.converter.OrderForm2oOrderDTOConverter;
import com.huo.dto.OrderDTO;
import com.huo.enums.ResultEnmu;
import com.huo.form.OrderForm;
import com.huo.sellException.SellException;
import com.huo.service.BuyerService;
import com.huo.service.OrderService;
import com.huo.until.ResultVOUtil;
import com.huo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 14:40 2020/4/30
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    BuyerService buyerService;

    /*创建订单/create*/
    @PostMapping("/create")
    public ResultVO<Map<String, String>> createOrder(@Valid OrderForm orderForm,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建订单 参数不正确orderForm={}", orderForm);
            throw new SellException(ResultEnmu.PARAM_ERROR.getCode()
                    , bindingResult.getFieldError().getDefaultMessage());

        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO = OrderForm2oOrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("购物车不能为空");
            throw new SellException(ResultEnmu.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    /*订单列表*/
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "2") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("查询订单列表 openID为空");
            throw new SellException(ResultEnmu.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);

        return ResultVOUtil.success(orderDTOPage.getContent());

    }

    /*订单详情*/
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    /*取消订单*/
    @PostMapping("/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        buyerService.cancelOrderOne(openid, orderId);

        return ResultVOUtil.success();
    }
}
