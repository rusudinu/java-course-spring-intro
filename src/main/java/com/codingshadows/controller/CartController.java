package com.codingshadows.controller;

import com.codingshadows.model.Cart;
import com.codingshadows.model.Product;
import com.codingshadows.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/sorted-by-total")
    public List<Cart> getCartsSortedByTotal() {
        return cartService.getAllCartsSortedByTotal();
    }

    @PostMapping("/{userId}/add-product")
    public void addProductToCart(@PathVariable Long userId, @RequestBody Product product) {
        cartService.addProductToCart(userId, product);
    }

    @DeleteMapping("/{userId}/remove-product/{productId}")
    public void removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeProductFromCart(userId, productId);
    }
}
