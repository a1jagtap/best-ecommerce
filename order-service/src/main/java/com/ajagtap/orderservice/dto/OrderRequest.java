package com.ajagtap.orderservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderRequest {
    private int items;
    private long price;
}
