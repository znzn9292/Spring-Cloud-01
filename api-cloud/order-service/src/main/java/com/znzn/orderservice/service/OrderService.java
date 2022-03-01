package com.znzn.orderservice.service;

import com.znzn.orderservice.dto.OrderDto;
import com.znzn.orderservice.jpa.OrderEntity;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderByOrderId(String orderId);

    Iterable<OrderEntity> getOrdersByUserId(String userId);


}
