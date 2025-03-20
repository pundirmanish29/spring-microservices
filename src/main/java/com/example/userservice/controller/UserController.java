package com.example.userservice.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;

@RestController
public class UserController {

    // Create a logger instance for this class
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * GET endpoint to retrieve all users.
     * 
     * @return ResponseEntity containing the list of users.
     */
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        logger.info("Fetching all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    /**
     * POST endpoint to create a new user.
     * 
     * @param user The user data sent in the request body.
     * @return ResponseEntity containing the created user.
     */
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        logger.info("Creating a new user: {}", user);
        User createdUser = userService.createUser(user);
        logger.info("User created successfully with id: {}", createdUser.getId());
        return ResponseEntity.ok(createdUser);
    }
    
    /**
     * PUT endpoint to update an existing user.
     * 
     * @param id The id of the user to update.
     * @param userDetails The new user data to update.
     * @return ResponseEntity containing the updated user or a 404 status if not found.
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id, 
            @Valid @RequestBody User userDetails) {
        logger.info("Updating user with id: {}", id);
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Update user details
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            User updatedUser = userService.createUser(user); // Alternatively, call a dedicated update method if available.
            logger.info("User updated successfully with id: {}", updatedUser.getId());
            return ResponseEntity.ok(updatedUser);
        } else {
            logger.warn("User not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * DELETE endpoint to remove a user.
     * 
     * @param id The id of the user to delete.
     * @return ResponseEntity with a status code indicating the outcome.
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with id: {}", id);
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            userService.deleteUser(id);
            logger.info("User deleted successfully with id: {}", id);
            return ResponseEntity.ok().build();
        } else {
            logger.warn("User not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
