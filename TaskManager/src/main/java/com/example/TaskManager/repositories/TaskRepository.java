package com.example.TaskManager.repositories;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;


public interface TaskRepository extends CrudRepository<Task, Integer>{
	
	
	@Query(value ="SELECT * FROM task t INNER JOIN user u ON t.id = u.id WHERE t.end_date BETWEEN ?1 AND ?2 AND u.id = ?3", nativeQuery= true)
	public Iterable<Task> findBeforeDate(Date date, Date weekFromNow, int id);
	
	@Query(value= "SELECT * FROM task t INNER JOIN user u ON t.id = u.id WHERE t.end_date = ?1 AND u.id=?2", nativeQuery=true)
	public Iterable<Task> findByEndDate(Date date, int id);
	
}
