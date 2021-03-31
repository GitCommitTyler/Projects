package com.example.TaskManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	public UserService us;
	
	
}
