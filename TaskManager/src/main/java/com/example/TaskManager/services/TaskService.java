package com.example.TaskManager.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.exceptions.TaskNotFoundException;
import com.example.TaskManager.repositories.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	UserService userService;
	
	Logger logger = LoggerFactory.getLogger(TaskService.class);
	public Iterable<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	public Task getTaskById(int id) {
		Optional<Task> foundTask = taskRepository.findById(id);
		if(!foundTask.isPresent()) {
			throw new TaskNotFoundException(id);
		}
		return foundTask.get();
	}
	
	public Iterable<Task> getTasksWithinWeek() {
		User taskUser = userService.GetUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		LocalDate dateNow = LocalDate.now();
		Date date = Date.from(dateNow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		Date weekFromNow =  Date.from(dateNow.plus(1, ChronoUnit.WEEKS).atStartOfDay(ZoneId.systemDefault()).toInstant());
		logger.info("USer ID"+ taskUser.getId());
		return taskRepository.findBeforeDate(date, weekFromNow, taskUser.getId());
	}
	
	public Iterable<Task> getTasksByDate(LocalDate date){
		User taskUser = userService.GetUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		logger.info("USer ID"+ taskUser.getId());
		return taskRepository.findByEndDate(utilDate, taskUser.getId());
	}
	
	public void saveOrUpdateTask(Task taskToAdd) {
		User taskUser = userService.GetUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		taskToAdd.setUser(taskUser);
		taskRepository.save(taskToAdd);
	}
	
}
