package com.hcl.data.dao.interfaces;

import java.util.List;

import com.hcl.data.entities.User;

public interface UserDao extends Dao<User,Long>{

	public List<User> findByUserName(String firstName);
	public User createUser(String username, String firstName, String lastName, String password);
	public boolean checkPassword(String password1, String password2);
}
