package com.codingshadows.controller;

import com.codingshadows.model.Order;
import com.codingshadows.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        // TODO
    }

    @PostMapping("/place/{userId}")
    public void placeOrder(@PathVariable Long userId, @RequestBody Order order) {
        // TODO
    }
}
