package com.ajagtap.orderservice.service;

import com.ajagtap.orderservice.dto.OrderDto;
import com.ajagtap.orderservice.dto.OrderRequest;
import com.ajagtap.orderservice.entity.Order;
import com.ajagtap.orderservice.exception.OrderNotFoundException;
import com.ajagtap.orderservice.mapper.OrderMapper;
import com.ajagtap.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Optional<OrderDto> findOrderByOrderId(String orderId) {
        Order order = orderRepository.findByOrderId(orderId).stream().findFirst().orElseThrow(OrderNotFoundException::new);
        return Optional.of(orderMapper.entityToDto(order));
    }

    @Override
    public List<OrderDto> findAllOrders() {
        ArrayList<OrderDto> orderDtos = new ArrayList<>();
        orderRepository.findAll().forEach(order -> orderDtos.add(orderMapper.entityToDto(order)));
        return orderDtos;
    }

    @Override
    public OrderDto saveOrder(OrderRequest orderDto) {
        Order order = orderMapper.dtoToEntity(orderDto);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.entityToDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(String orderId, OrderDto orderDto) {
        Order originalOrder = orderRepository.findByOrderId(orderId).stream().findFirst().orElseThrow(OrderNotFoundException::new);
        Order savedOrder = orderRepository.save(orderMapper.updateDtoToEntity(orderDto, originalOrder));
        return orderMapper.entityToDto(savedOrder);
    }

    @Override
    public void deleteOrder(String orderId) {
        Order originalOrder = orderRepository.findByOrderId(orderId).stream().findFirst().orElseThrow(OrderNotFoundException::new);
        orderRepository.delete(originalOrder);
    }
}
