package com.codingshadows.service;

import com.codingshadows.model.Cart;
import com.codingshadows.model.Product;
import com.codingshadows.model.User;
import com.codingshadows.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private UserRepository userRepository;

    public List<Cart> getAllCartsSortedByTotal() {
        // TODO implement this method
    }

    public double calculateTotal(Cart cart) {
        return cart.getProducts().stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }

    public void addProductToCart(Long userId, Product product) {
        // TODO implement this method
    }

    public void removeProductFromCart(Long userId, Long productId) {
        // TODO implement this method
    }

    public Cart getCartByUserId(Long id) {
        return userRepository.findById(id).orElseThrow().getCart();
    }
}
