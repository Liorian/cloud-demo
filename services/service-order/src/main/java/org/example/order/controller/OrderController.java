package org.example.order.controller;


import org.example.order.bean.Order;
import org.example.order.properties.OrderProperties;
import org.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    private OrderProperties orderProperties;

    @GetMapping("/config")
    public String getConfig() {
        return "order.timeout=" + orderProperties.getTimeout() + "； " +
                "order.auto-confirm=" + orderProperties.getAutoConfirm();
    }

    @GetMapping("/create")
    public Order createOder(@RequestParam("productId") Long productId,
                            @RequestParam("userId") Long userId) {
        Order order = orderService.createOder(productId, userId);
        return order;
    }

}