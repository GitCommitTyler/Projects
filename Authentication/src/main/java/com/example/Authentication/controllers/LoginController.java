package com.example.Authentication.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.Authentication.entities.User;
import com.example.Authentication.services.UserService;


@Controller
public class LoginController {
	
	Logger logger =  LoggerFactory.getLogger(LoginController.class);
	@Autowired
	public UserService us;
	
    @GetMapping("/")
    public String showGreeting(ModelMap map) {
        return "greeting";
    }


    @GetMapping("/login")
    public String showLogin(ModelMap map) {
        return "login";
    }

    @PostMapping("/login")
    public String submitLogin(@RequestParam String username, @RequestParam String password){

    	if(us.GetUserByEmail(username) != null) {
    		return "success";}
    	else
    		return "error";
        
    }
    
    @GetMapping("/register")
    public String showRegister(ModelMap model) {
    	return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, ModelMap model) {
	      model.addAttribute("email", user.getEmail());
	      model.addAttribute("name", user.getName());
	      model.addAttribute("password", user.getPassword());
	      user.setEmail((String) model.getAttribute("email"));
	      user.setName((String) model.getAttribute("name"));
	      user.setPassword((String) model.getAttribute("password"));
	      us.UpdateUser(user);
	      return "login";
    }
}
