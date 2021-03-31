package com.example.TaskManager.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name="task")
@Data
public class Task {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int taskId;

    @NotEmpty(message="Give task a name")
	private String taskName;

    @NotNull(message="Enter a start date")
    @FutureOrPresent(message="Date cannot be in the past")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
    
    @NotNull(message="Enter an end date")
    @FutureOrPresent(message="Date cannot be in the past")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
    
    @NotEmpty(message="Enter a description")
    @Length(min=10, max=255, message="Keep description between 10 and 255 characters")
	private String description;
    
    @NotNull(message="Set severity")
	private Severity severity;
	
	@ManyToOne
	@JoinColumn(name="id")	
	private User user;
}
