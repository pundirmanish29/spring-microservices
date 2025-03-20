package com.example.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;

class UserServiceTest {

    // Create a mock of the UserRepository
    @Mock
    private UserRepository userRepository;

    // Inject the mock into the UserService
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Prepare test data
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Alice");
        user1.setEmail("alice@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Bob");
        user2.setEmail("bob@example.com");

        // Configure mock repository to return test data
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Execute the service method
        List<User> users = userService.getAllUsers();

        // Verify results
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserByIdFound() {
        // Prepare test data
        User user = new User();
        user.setId(1L);
        user.setName("Alice");
        user.setEmail("alice@example.com");

        // Configure mock repository to return user when id is queried
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Execute the service method
        Optional<User> foundUser = userService.getUserById(1L);

        // Verify results
        assertTrue(foundUser.isPresent());
        assertEquals("Alice", foundUser.get().getName());
    }

    @Test
    void testGetUserByIdNotFound() {
        // Configure repository to return empty Optional for a non-existent user
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.getUserById(99L);

        // Verify that no user is found
        assertFalse(foundUser.isPresent());
    }
}
