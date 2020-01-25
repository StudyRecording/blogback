package com.hu.blogback.service;

import com.hu.blogback.pojo.Order;

public interface OrderService {

    /**
     * 存储订单
     */
    Order save(Order order);
}
