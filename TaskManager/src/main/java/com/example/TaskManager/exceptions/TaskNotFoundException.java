package com.example.TaskManager.exceptions;

public class TaskNotFoundException extends RuntimeException {
	
	public TaskNotFoundException(int id) {
		super("Task with id: " + id + "not found");
	}
	
	public TaskNotFoundException(String name) {
		super("Task with id: " + name + "not found");
	}
}
