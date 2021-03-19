package com.example.UserFeedback.controllers;
import com.example.UserFeedback.entities.Feedback;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestFormController {
	
//  Didn't use this 
//	Logger logger = LoggerFactory.getLogger(FeedbackController.class);
//	@GetMapping(value="/")
//	public ModelAndView testForm(ModelMap model) {
//		return new ModelAndView("index", "feedback", new Feedback());
//	}
//	@RequestMapping(value="/submit", method=RequestMethod.POST)
//	public @ResponseBody submit(Model model, @ModelAttribute("feedback") Feedback fb ) {
//		logger.info(fb.toString());
//		ObjectMapper mapper = new ObjectMapper();
//		model.addAttribute("comments", fb.getComments());
//		model.addAttribute("rating", fb.getRating());
//		model.addAttribute("user", fb.getUser());
//		fb.setComments((String) model.getAttribute("comments"));
//		fb.setRating((int) model.getAttribute("rating"));
//		fb.setUser((String) model.getAttribute("user"));
//		try {
//			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fb);
//			logger.info("feedback:" + jsonString);
//			return jsonString;
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

}
