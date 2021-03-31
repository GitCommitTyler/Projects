package com.example.TaskManager.repositories;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.repository.CrudRepository;

import com.example.TaskManager.entities.User;

@AutoConfigurationPackage
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmail(String email);
    public User findByUserName(String userName);
}