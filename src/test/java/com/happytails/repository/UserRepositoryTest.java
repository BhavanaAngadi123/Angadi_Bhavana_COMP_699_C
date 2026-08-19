package com.happytails.repository;

import com.happytails.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository users;

    @Test
    void savesAndFindsUserByEmail() {
        User user = new User();
        user.setName("Test Owner");
        user.setEmail("owner@example.com");
        user.setPasswordHash("encoded-password");
        user.setRole("owner");

        users.save(user);

        assertThat(users.findByEmail("owner@example.com"))
                .isPresent()
                .get()
                .extracting(User::getName)
                .isEqualTo("Test Owner");
        assertThat(users.existsByEmail("owner@example.com")).isTrue();
    }
}
