package com.example.TaskManager.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

//import lombok.NonNull;

@Entity 
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="userName", length=50, unique=true)
    @NotEmpty(message="Username cannot be empty")
    @Length(min=8, message="username must have at least 8 characters")
    private String userName;
    
    @Column(name="firstName")
	@NotEmpty(message="First Name cannot be empty")
    private String firstName;
    
    @Column(name="lastName")
    @NotEmpty(message="Last Name cannot be empty")
    private String lastName;
    
    @Column(name="email")
    @NotEmpty(message="Email cannot be empty")
    @Email(message= "Please input a valid email address")
    private String email;
    
    @Column(name="password")
    @NotEmpty(message="Password cannot be empty")
    @Length(min=8, message="Password must be at least 8 characters")
    private String password;
    
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    @OneToMany
    @JoinColumn(name="id", nullable=true)
    private List<Task> tasks = new ArrayList<Task>();
        
    public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
    	return (id.toString() + " " + userName + " " + firstName + " " + lastName + " " + email + " " + password);
    }
    
}