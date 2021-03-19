package com.example.UserFeedback.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.UserFeedback.Exceptions.InvalidFeedbackException;

@ControllerAdvice
public class ErrorController {
	@ExceptionHandler(value = InvalidFeedbackException.class)
    public ResponseEntity<Object> exception(InvalidFeedbackException exception) {
		String content = 
	              "Feedback was in invalid format\n"+exception.getMessage();
	    HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.setContentType(MediaType.TEXT_HTML);
		
       return new ResponseEntity<>(content, responseHeaders, HttpStatus.NOT_ACCEPTABLE);
    }

}
