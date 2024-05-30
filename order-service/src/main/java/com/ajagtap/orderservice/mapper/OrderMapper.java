package com.ajagtap.orderservice.mapper;

import com.ajagtap.orderservice.dto.OrderDto;
import com.ajagtap.orderservice.dto.OrderRequest;
import com.ajagtap.orderservice.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order dtoToEntity(OrderRequest orderDto) {
        return Order.builder()
                .price(orderDto.getPrice())
                .items(orderDto.getItems())
                .build();
    }

    public Order updateDtoToEntity(OrderDto orderDto, Order orderEntity) {
        orderEntity.setPrice(orderDto.getPrice());
        orderEntity.setItems(orderDto.getItems());
        return orderEntity;
    }

    public OrderDto entityToDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getId() + "")
                .price(order.getPrice())
                .items(order.getItems())
                .build();
    }

}
