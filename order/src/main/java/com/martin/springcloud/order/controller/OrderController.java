package com.martin.springcloud.order.controller;


import com.martin.springcloud.order.domain.OrderDo;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/{id}")
    @ResponseBody
    public OrderDo getOrder(@PathVariable Long id) {
        OrderDo orderDo = new OrderDo();
        orderDo.setId(id);
        orderDo.setPrice(new BigDecimal("22.36"));
        orderDo.setOrderQty(7);
        System.out.println("order 查询");
        return orderDo;
    }

}
