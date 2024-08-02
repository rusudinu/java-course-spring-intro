package com.codingshadows.controller;

import com.codingshadows.model.User;
import com.codingshadows.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        // TODO
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        // TODO
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        // TODO
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // TODO
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // TODO
    }

    @GetMapping("/sorted-by-orders")
    public List<User> getUsersSortedByOrders() {
        // TODO
    }
}
