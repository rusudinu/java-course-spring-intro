package com.codingshadows.controller;

import com.codingshadows.model.Product;
import com.codingshadows.model.User;
import com.codingshadows.repository.CartRepository;
import com.codingshadows.repository.OrderRepository;
import com.codingshadows.repository.UserRepository;
import com.codingshadows.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WishlistControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setName("John Doe");
        testUser = userRepository.save(testUser);
    }

    @Test
    public void testAddProductToWishlist() {
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(10.0);
        product.setQuantity(2);

        HttpEntity<Product> entity = new HttpEntity<>(product);

        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/wishlists/" + testUser.getId() + "/add-product",
                HttpMethod.POST,
                entity,
                Void.class
        );

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(wishlistService.getWishlist(testUser.getId()).getProducts()).hasSize(1);
        assertThat(wishlistService.getWishlist(testUser.getId()).getProducts().get(0).getName()).isEqualTo("Product 1");
    }

    @Test
    public void testRemoveProductFromWishlist() {
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(10.0);
        product.setQuantity(2);

        wishlistService.addProductToWishlist(testUser.getId(), product);
        Long productId = wishlistService.getWishlist(testUser.getId()).getProducts().get(0).getId();

        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/wishlists/" + testUser.getId() + "/remove-product/" + productId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(wishlistService.getWishlist(testUser.getId()).getProducts()).isEmpty();
    }
}
