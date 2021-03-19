package com.example.UserFeedback.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@ToString
public class Feedback {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter private Integer id;
	@Getter @Setter private String comments;
	@Getter @Setter private int rating;
	@Getter @Setter private String user;
}
