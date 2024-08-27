package com.chengannagari.s.dashboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.chengannagari.s.dashboard.Entity.Role;
import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;

@SpringBootTest
class UserRepositoryTestCase {
	@Mock
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        // Mock the behavior of the userRepository.findAll() method
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // Call the method you want to test
        List<User> users = userRepository.findAll();

        // Assert that the list is empty
        assertEquals(0, users.size());
    }

    @Test
    public void testFindByFirstName() {
        String firstName = "John";
        User user = new User();
        user.setFirstName(firstName);
        when(userRepository.findByFirstName(firstName)).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findByFirstName(firstName);

        assertTrue(result.isPresent());
        assertEquals(firstName, result.get().getFirstName());
    }
    @Test
    public void testExistsByFirstName() {
        String firstName = "John";
        when(userRepository.existsByFirstName(firstName)).thenReturn(true);

        boolean result = userRepository.existsByFirstName(firstName);

        assertTrue(result);
    }

    @Test
    public void testUpdatePasswordByEmail() {
        String email = "john@example.com";
        String newPassword = "newPassword";
        userRepository.updatePasswordByEmail(email, newPassword);

        verify(userRepository).updatePasswordByEmail(email, newPassword);
    }


}
