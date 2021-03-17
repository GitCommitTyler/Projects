package UserManager.controllers;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import UserManager.entities.User;
import UserManager.exceptions.UserNotFoundException;
import UserManager.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
    Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@GetMapping("/users")
	public String showUsers(ModelMap model) {
		
		
		logger.info("Getting all Users");
		Iterable<User> users = userService.GetAllUsers();
		for(User u : users) {
			logger.info("User: "+u.getName());
		}
		logger.info("Passing users to view");
	    model.addAttribute("users2", users);    
		
        return "users";
    }
	
	@RequestMapping(value = "/input", method = RequestMethod.GET)
	   public ModelAndView getInput() {
	      return new ModelAndView("input", "user", new User());
	   }
	   @RequestMapping(value = "/inputID", method = RequestMethod.POST)
	      public ModelAndView inputID(@ModelAttribute("user")User user,  ModelMap model) {
	      model.addAttribute("id", user.getId());
	    	  if(userService.GetUserById((int)model.getAttribute("id"))==null)
	    		  throw new UserNotFoundException();
	    	  	
	    	  else
	    		  model.addAttribute("user", userService.GetUserById((int)model.getAttribute("id")));
		      
		      logger.info("input function1: "+model.getAttribute("user"));
		      
		      user = (User)model.getAttribute("user");
		      logger.info("input function2: " +user);
		      logger.info("User entered"+user+ " "+ user.getId());
	      
	      
	      return new ModelAndView("result", "user", user);
	   }
	   
		@RequestMapping(value = "/result", method = RequestMethod.GET)
	   public ModelAndView changeUser(@ModelAttribute("user")User user, 
			   
			   ModelMap model) {
		System.out.println("changeUser function: "+model);
			User foundUser = userService.GetUserById(((User)model.getAttribute("user")).getId());
	      return new ModelAndView("result", "user", foundUser);
	   }
		
		@RequestMapping(value = "/submitChange", method = RequestMethod.POST)
		public String submitChange(@ModelAttribute("user")User user, 
	   ModelMap model) {
	      model.addAttribute("email", user.getEmail());
	      model.addAttribute("name", user.getName());
	      model.addAttribute("password", user.getPassword());
	      user.setEmail((String) model.getAttribute("email"));
	      user.setName((String) model.getAttribute("name"));
	      user.setPassword((String) model.getAttribute("password"));
	      logger.info("updated user: "+user);
	      userService.UpdateUser(user);
	      return "redirect:/success";
	   }
		
		@RequestMapping(value="/success")
		public String showSuccess() {
			return "success";
		
		}
	

}