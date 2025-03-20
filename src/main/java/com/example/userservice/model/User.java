package com.example.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // Changed table name to 'users'
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates constructor with no parameters
@AllArgsConstructor // Generates constructor with all parameters
@Builder // Generates builder pattern methods
public class User {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotBlank(message = "Name is mandatory")
	    private String name;
	    
	    @NotBlank(message = "Email is mandatory")
	    @Email(message = "Please provide a valid email address")
	    private String email;
}
