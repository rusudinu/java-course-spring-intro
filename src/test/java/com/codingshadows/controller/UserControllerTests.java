package com.codingshadows.controller;

import com.codingshadows.model.User;
import com.codingshadows.repository.UserRepository;
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
public class UserControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("John Doe");

        ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:" + port + "/api/users", user, User.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getName()).isEqualTo("John Doe");
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setName("John Doe");
        user = userRepository.save(user);

        ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:" + port + "/api/users/" + user.getId(), User.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getName()).isEqualTo("John Doe");
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("John Doe");
        user = userRepository.save(user);

        user.setName("Jane Doe");

        HttpEntity<User> entity = new HttpEntity<>(user);

        ResponseEntity<User> response = restTemplate.exchange("http://localhost:" + port + "/api/users/" + user.getId(), HttpMethod.PUT, entity, User.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getName()).isEqualTo("Jane Doe");
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setName("John Doe");
        user = userRepository.save(user);

        restTemplate.delete("http://localhost:" + port + "/api/users/" + user.getId());

        assertThat(userRepository.findById(user.getId())).isEmpty();
    }
}

