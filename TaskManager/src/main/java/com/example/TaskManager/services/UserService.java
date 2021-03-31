package com.example.TaskManager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.entities.UserRole;
import com.example.TaskManager.exceptions.UserNotFoundException;
import com.example.TaskManager.repositories.UserRepository;



@Service
@AutoConfigurationPackage
public class UserService {
	
	@Autowired
	 private UserRepository userRepository;
	
	@Autowired 
	private BCryptPasswordEncoder bCrypt;
	
	

    public Iterable<User> GetAllUsers()
    {
        return userRepository.findAll();
    }


    public User GetUserByEmail(String email) {
        User foundUser = userRepository.findByEmail(email);
        return foundUser;
    }
    
    public User GetUserByUserName(String userName) {
        User foundUser = userRepository.findByUserName(userName);
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
    	usertoUpdate.setPassword(bCrypt.encode(usertoUpdate.getPassword()));
    	usertoUpdate.setRole(UserRole.ADMIN);
    	userRepository.save(usertoUpdate);
    }


}
