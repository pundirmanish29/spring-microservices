package com.example.userservice.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUsers() throws Exception {
        // Simulate a GET request to /users endpoint and expect a JSON array response
        mockMvc.perform(get("/users"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", isA(java.util.List.class)));
    }
    
    @Test
    void testCreateUser() throws Exception {
        // JSON payload for creating a user
        String userJson = "{ \"name\": \"Alice\", \"email\": \"alice@example.com\" }";
        
        // Simulate a POST request to /users endpoint
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.name", is("Alice")))
               .andExpect(jsonPath("$.email", is("alice@example.com")));
    }
    
    // Further tests for PUT and DELETE endpoints can be added similarly.
}
