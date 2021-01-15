package com.cafe.service;

import java.util.List;

import com.cafe.dao.UserDao;
import com.cafe.dao.UserDaoHibernate;
import com.cafe.models.User;

public class UserService {

	public static UserDao uDao = new UserDaoHibernate();
	
	public static void createUser(User user) {
		uDao.insert(user);
	}
	
	public static User verifyUser(String username, String password) {
		return uDao.verifyUser(username, password);
	}
	
	public static List<User> getUsers() {
		return uDao.getUsers();
	}
	
	public static void deleteUser(User user) {
		uDao.delete(user);
	}
}
