package com.ajagtap.orderservice.controller;

import com.ajagtap.orderservice.dto.OrderDto;
import com.ajagtap.orderservice.dto.OrderRequest;
import com.ajagtap.orderservice.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private IOrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String orderId) {
        Optional<OrderDto> orderDto = orderService.findOrderByOrderId(orderId);
        return orderDto.map(o -> new ResponseEntity(o, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @PostMapping("/")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderRequest orderDto) {
        return new ResponseEntity<>(orderService.saveOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable String orderId, @RequestBody OrderDto orderDto) {
        OrderDto updatedOrderDto = orderService.updateOrder(orderId, orderDto);
        if (updatedOrderDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(String.format("Order updated with orderId: {}", orderId), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(String.format("Order deleted with orderId: {}", orderId), HttpStatus.OK);
    }
}
