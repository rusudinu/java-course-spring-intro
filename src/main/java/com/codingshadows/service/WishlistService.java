package com.codingshadows.service;

import com.codingshadows.model.Product;
import com.codingshadows.model.User;
import com.codingshadows.model.Wishlist;
import com.codingshadows.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private UserRepository userRepository;

    public void addProductToWishlist(Long userId, Product product) {
        // TODO implement this method
    }

    public void removeProductFromWishlist(Long userId, Long productId) {
        // TODO implement this method
    }

    public Wishlist getWishlist(Long userId) {
        // TODO implement this method
    }
}
