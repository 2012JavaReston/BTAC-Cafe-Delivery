package com.cafe.dao;

import java.util.List;

import com.cafe.models.User;

public interface UserDao {
	public void insert(User user);
	
	public User verifyUser(String username, String password);
	
	public List<User> getUsers();
	
	public void delete(User user);
}
