package com.codingshadows.controller;

import com.codingshadows.model.Product;
import com.codingshadows.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{userId}/add-product")
    public void addProductToWishlist(@PathVariable Long userId, @RequestBody Product product) {
        wishlistService.addProductToWishlist(userId, product);
    }

    @DeleteMapping("/{userId}/remove-product/{productId}")
    public void removeProductFromWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.removeProductFromWishlist(userId, productId);
    }
}
