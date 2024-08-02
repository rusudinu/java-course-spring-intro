package com.codingshadows.controller;

import com.codingshadows.model.Cart;
import com.codingshadows.model.Order;
import com.codingshadows.model.Product;
import com.codingshadows.model.User;
import com.codingshadows.repository.CartRepository;
import com.codingshadows.repository.OrderRepository;
import com.codingshadows.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        cartRepository.deleteAll();
        orderRepository.deleteAll();

        testUser = new User();
        testUser.setName("John Doe");
        testUser = userRepository.save(testUser);
    }

    @Test
    public void testPlaceOrder() {
        Order order = new Order();
        order.setCompleted(false);
        order.setUser(testUser);

        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(10.0);
        product.setQuantity(2);

        Cart cart = new Cart();
        cart.setProducts(List.of(product));
        cart = cartRepository.save(cart);
        order.setCart(cart);

        HttpEntity<Order> entity = new HttpEntity<>(order);

        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/orders/place/" + testUser.getId(),
                HttpMethod.POST,
                entity,
                Void.class
        );

        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        List<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1);

        Order savedOrder = orders.get(0);
        assertThat(savedOrder.isCompleted()).isTrue();
        assertThat(savedOrder.getUser().getId()).isEqualTo(testUser.getId());
        assertThat(savedOrder.getCart().getProducts()).hasSize(1);
        assertThat(savedOrder.getCart().getProducts().get(0).getName()).isEqualTo("Product 1");
    }

    @Test
    public void testGetAllOrders() {
        Order order1 = new Order();
        order1.setCompleted(true);
        order1.setUser(testUser);

        Order order2 = new Order();
        order2.setCompleted(true);
        order2.setUser(testUser);

        orderRepository.save(order1);
        orderRepository.save(order2);

        ResponseEntity<Order[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/orders",
                Order[].class
        );

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
    }
}
