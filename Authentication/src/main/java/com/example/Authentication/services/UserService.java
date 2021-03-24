package com.example.Authentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import com.example.Authentication.entities.User;
import com.example.Authentication.exceptions.UserNotFoundException;
import com.example.Authentication.repositories.UserRepository;



@Service
@AutoConfigurationPackage
public class UserService {
	
	@Autowired
	 private UserRepository userRepository;
	
	

    public Iterable<User> GetAllUsers()
    {
        return userRepository.findAll();
    }


    public User GetUserByEmail(String email) {
        User foundUser = userRepository.findByEmail(email);
        if(foundUser == null) {
        	throw new UserNotFoundException(email);
        }
        return foundUser;
    }
    
    public User GetUserById(int id) {
    	Optional<User> foundUser = userRepository.findById(id);
    	
    	
    	
    	
    	if (!foundUser.isPresent()) {
    		
    		throw new UserNotFoundException(id);
    	}
    	
    	return(foundUser.get());
    }
    
    public void UpdateUser(User usertoUpdate) {
    	userRepository.save(usertoUpdate);
    }


}
