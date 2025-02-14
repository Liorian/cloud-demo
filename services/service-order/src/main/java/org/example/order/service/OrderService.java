package org.example.order.service;


import org.example.order.bean.Order;

public interface OrderService {

    Order createOder(Long productId, Long userId);
}
