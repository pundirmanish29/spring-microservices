package com.example.userservice.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Create or update a user
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    // Retrieve a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
