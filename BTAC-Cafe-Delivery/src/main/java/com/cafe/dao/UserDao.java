package com.cafe.dao;

import com.cafe.models.User;

public interface UserDao {
	public void insert(User user);
	
	public User verifyUser(String username, String password);
	
	public void delete(User user);
}
