package com.example.TaskManager.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UserNotFoundException(int id){
		 super("User " + id + " not in database" );
	 }
    public UserNotFoundException(String email){
		 super("User " + email + " not in database" );
	 }
}
