package com.example.TaskManager.repositories;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskManager.entities.Task;


public interface TaskRepository extends CrudRepository<Task, Integer>{
	
	
	@Query("SELECT t FROM Task t WHERE t.endDate BETWEEN ?1 AND ?2")
	public Iterable<Task> findBeforeDate(Date date, Date weekFromNow);
	
	public Iterable<Task> findByEndDate(Date date);
	
}
