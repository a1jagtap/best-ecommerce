package com.ajagtap.orderservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderDto {
    private String orderId;
    private int items;
    private long price;
}
