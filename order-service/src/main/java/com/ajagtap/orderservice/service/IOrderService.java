package com.ajagtap.orderservice.service;

import com.ajagtap.orderservice.dto.OrderDto;
import com.ajagtap.orderservice.dto.OrderRequest;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Optional<OrderDto> findOrderByOrderId(String orderId);

    List<OrderDto> findAllOrders();

    OrderDto saveOrder(OrderRequest orderDto);

    OrderDto updateOrder(String orderId, OrderDto orderDto);

    void deleteOrder(String orderId);
}
