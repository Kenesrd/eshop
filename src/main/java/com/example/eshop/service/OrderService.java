package com.example.eshop.service;

import com.example.eshop.domain.Order;

public interface OrderService {
    void saveOrder(Order order);

    Order getOrder(Long id);
}
