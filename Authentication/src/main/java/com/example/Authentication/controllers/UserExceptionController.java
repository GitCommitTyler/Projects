package com.example.Authentication.controllers;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Authentication.exceptions.UserNotFoundException;

@ControllerAdvice
public class UserExceptionController {

	@ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		String content = 
	               "<header>"
				 +"<h2>Application has encountered an exception: <h2>"	
	             + "<h3><span>User not found</span></h3>"
				 + "<p>"+sw.toString()+"</p>"
	             + "</header>";
	    HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.setContentType(MediaType.TEXT_HTML);
		
       return new ResponseEntity<>(content, responseHeaders, HttpStatus.NOT_FOUND);
    }
	
	


}