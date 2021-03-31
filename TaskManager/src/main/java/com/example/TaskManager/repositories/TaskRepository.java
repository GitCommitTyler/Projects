package com.example.TaskManager.repositories;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;


public interface TaskRepository extends CrudRepository<Task, Integer>{
	
	
	@Query("SELECT t FROM Task t, User u WHERE t.endDate BETWEEN ?1 AND ?2 AND u.id = ?3")
	public Iterable<Task> findBeforeDate(Date date, Date weekFromNow, int id);
	
	@Query("SELECT t FROM Task t, User u WHERE t.endDate = ?1 AND u.id=?2")
	public Iterable<Task> findByEndDate(Date date, int id);
	
}
