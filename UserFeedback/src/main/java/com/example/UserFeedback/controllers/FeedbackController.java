package com.example.UserFeedback.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserFeedback.Exceptions.InvalidFeedbackException;
import com.example.UserFeedback.entities.Feedback;
import com.example.UserFeedback.services.FeedbackService;

@RestController
public class FeedbackController {
	
	Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	@Autowired
	FeedbackService feedbackService;
	
	@GetMapping("/feedback")
	public @ResponseBody Iterable<Feedback> getAllFeedbacks() {
        // This returns a JSON or XML with the Feedbacks
        return feedbackService.GetAllFeedback();
    }
	
	//in order to receive requests from html form, i had to use this settings.
	//other methods like Curl and Postman should still work fine.
	@CrossOrigin(origins = "null")
	@PostMapping(value="/feedback",
			 consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE},
		        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity addNewFeedback(@RequestBody Feedback feedback) {
		if(feedback.getComments().matches(""))
		{
			throw new InvalidFeedbackException("comments weren't entered");
		}
		else if(feedback.getRating()==0)
		{
			throw new InvalidFeedbackException("rating wasn't entered");
		}
		else if(feedback.getUser().matches(""))
		{
			throw new InvalidFeedbackException("name was not entered");
		}
		feedbackService.saveOrUpdateFeedback(feedback);
		String content = 
	              "Feedback was added successfully";
	    HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.setContentType(MediaType.TEXT_HTML);
		
     return new ResponseEntity<>(content, responseHeaders, HttpStatus.ACCEPTED);
		
	}
	

}
