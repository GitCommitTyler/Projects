package com.example.Security;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.repositories.UserRepository;
import com.example.TaskManager.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EntityTests {

	@Test
	public void WhenSetPassword_CheckGetPassword() {
		User testUser = new User();
		
		testUser.setPassword("mypassword");
		assertEquals(testUser.getPassword(),"mypassword");
	}
	
}
