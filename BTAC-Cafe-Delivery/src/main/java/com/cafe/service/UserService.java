package com.cafe.service;

import java.util.List;

import com.cafe.dao.UserDao;
import com.cafe.dao.UserDaoHibernate;
import com.cafe.models.User;

public class UserService {

	public static UserDao uDao = new UserDaoHibernate();
	
	//Calls the create user function of the UserDao.
	public static void createUser(User user) {
		uDao.insert(user);
	}
	
	//Calls the verify user function of the UserDao and returns the user if the given credentials are valid.
	public static User verifyUser(String username, String password) {
		return uDao.verifyUser(username, password);
	}
	
	//Calls the getAllUsers function of the UserDao and returns all users
	public static List<User> getUsers() {
		return uDao.getUsers();
	}
	
	//Calls the delete user functionality of the UserDao.
	public static void deleteUser(User user) {
		uDao.delete(user);
	}
}
