package org.example.order.controller;


import org.example.order.bean.Order;
import org.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Value("${order.timeout}")
    String orderTimeout;

    @Value("${order.auto-confirm}")
    String orderAutoConfirm;

    @GetMapping("/config")
    public String getConfig() {
        return "order.timeout=" + orderTimeout + "； " +
                "order.auto-confirm=" + orderAutoConfirm;
    }

    @GetMapping("/create")
    public Order createOder(@RequestParam("productId") Long productId,
                            @RequestParam("userId") Long userId) {
        Order order = orderService.createOder(productId, userId);
        return order;
    }

}