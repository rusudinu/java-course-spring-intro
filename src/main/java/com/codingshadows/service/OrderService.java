package com.codingshadows.service;

import com.codingshadows.model.Order;
import com.codingshadows.model.User;
import com.codingshadows.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.codingshadows.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    // TODO inject the OrderRepository

    public List<User> getAllUsersSortedByNumberOfOrders() {
        // TODO implement this method
    }

    public void placeOrder(Long userId, Order order) {
        // TODO implement this method
    }

    public List<Order> getAllOrders() {
        // TODO implement this method
    }
}
