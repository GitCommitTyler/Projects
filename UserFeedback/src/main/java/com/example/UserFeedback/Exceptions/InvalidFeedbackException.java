package com.example.UserFeedback.Exceptions;

public class InvalidFeedbackException extends RuntimeException{
	 private static final long serialVersionUID = 1L;
	 public InvalidFeedbackException(String reason){
		 super(reason);
	 }
}
