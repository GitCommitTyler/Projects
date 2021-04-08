package com.example.TaskManager.controllers;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.UserService;


@Controller
public class LoginController {
	
	Logger logger =  LoggerFactory.getLogger(LoginController.class);
	@Autowired
	public UserService us;
	

    @GetMapping(value={"/login"})
    public String showLogin(ModelMap map) {
    	User user = new User();
    	map.addAttribute("user", user);
        return "loginform";
    }

    @PostMapping(value={"/login"})
    public String submitLogin(@RequestParam String username, @RequestParam String password, BindingResult bindingResult){
    	User userExists = us.GetUserByUserName(username);
//    	if(userExists == null) {
//    		bindingResult.rejectValue("username", "error.user", "Username not found");
//    	}
    	if(userExists!=null) {
    		
    		return "addtask";
    		}
    		
    	else
    	{
    		logger.info(us.GetUserByUserName(username).toString());
    		return "loginform";
    	}
    }
    
    @GetMapping("/register")
    public String showRegister(ModelMap model) {
    	model.addAttribute("user", new User());
    	return "registerform";
    }
    
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
    	
    	logger.info("USER ENTERED: " + user.getFirstName()+ " " + user.getLastName()+ " " +user.getUserName()+ " " +user.getEmail()+ " " +user.getPassword());
	      User userExists = us.GetUserByUserName(user.getUserName());
	      if (userExists != null) {
	            bindingResult
	                    .rejectValue("userName", "error.user",
	                            "There is already a user registered with the user name provided");
	        }

	      if (bindingResult.hasErrors()) {
	    	  //redirectAttrs.addFlashAttribute("errors", bindingResult.getAllErrors());
	          return "registerform";
	    	} 
	      else {
    		us.UpdateUser(user);
    	    return "redirect:login";
	      }
	    	   
	    	
	    
    }
    
    @GetMapping("/success")
    public String showSuccess(ModelMap map){
    	ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = us.GetUserByUserName(auth.getName());
        map.addAttribute("user", user.getFirstName()+ " " + user.getLastName());
        return "successform";
    }
    
}
