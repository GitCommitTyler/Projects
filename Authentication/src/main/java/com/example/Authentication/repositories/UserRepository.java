package com.example.Authentication.repositories;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.repository.CrudRepository;

import com.example.Authentication.entities.User;

@AutoConfigurationPackage
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmail(String email);
}