package com.example.TaskManager.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.services.TaskService;

@Controller
public class TaskController {
	
	Logger logger =  LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	TaskService taskService;
	
	public List<LocalDate> getDatesBetween(
			  LocalDate startDate, LocalDate endDate) { 

			    long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate); 
			    return IntStream.iterate(0, i -> i + 1)
			      .limit(numOfDaysBetween)
			      .mapToObj(i -> startDate.plusDays(i))
			      .collect(Collectors.toList()); 
			}
	
	@GetMapping("/tasks")
	public String showTasks(ModelMap map) {
		Map<LocalDate, Iterable<Task>> dateMap = new HashMap<LocalDate, Iterable<Task>>();
		Iterable<Task> tasks = taskService.getTasksWithinWeek();
		LocalDate dateNow = LocalDate.now();
		LocalDate dateThen = dateNow.plus(52, ChronoUnit.WEEKS);
		List<LocalDate> dates = getDatesBetween(dateNow, dateThen);
		dates.forEach(x -> dateMap.put(x, taskService.getTasksByDate(x)));
		dateMap.entrySet().forEach(x-> logger.info(x.getValue().toString()));
		map.addAttribute("tasks", tasks);
		map.addAttribute("week", dates);
		map.addAttribute("task", new Task());
		map.addAttribute("dateMap", dateMap);
		tasks.forEach(x -> logger.info(x.toString()));
		return "taskpage";
	}
	
	@GetMapping("/addtasks")
	public String newTask(ModelMap map) {
		map.addAttribute(new Task());
		return "addtask";
	}
	
	@PostMapping("/addtasks")
	public String saveTasks(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
		logger.info("USER ENTERED: "+task.getTaskName()+" "+task.getDescription()+" " + task.getStartDate()+" " + task.getEndDate()+" "+task.getSeverity());
		if(bindingResult.hasErrors()) {
			return "addtask";
		}
		taskService.saveOrUpdateTask(task);
		return "addtask";
	}
	
	@RequestMapping(value = "/updatetasks", method = RequestMethod.POST)
	public String updateTasks(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
		logger.info("USER ENTERED: "+task.getTaskName()+" "+task.getDescription()+" " + task.getStartDate()+" " + task.getEndDate()+" "+task.getSeverity());
		if(bindingResult.hasErrors()) {	
			return "redirect:/tasks";
		}
		taskService.saveOrUpdateTask(task);
		return "redirect:/tasks";
		
	}
}
