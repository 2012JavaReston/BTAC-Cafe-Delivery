package com.cafe.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cafe.controllers.UserController;
import com.cafe.models.User;
import com.cafe.util.HibernateUtil;


public class UserDaoHibernate implements UserDao {
	private final static Logger Log = Logger.getLogger(UserDaoHibernate.class);
	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(user);
		
		tx.commit();
		ses.close();
	}

	@Override
	public User verifyUser(String username, String password) {
		// TODO Auto-generated method stub
		Log.info("Username: " + username);
		Log.info("Password: " + password);
		Session ses = HibernateUtil.getSession();
		User user = ses.createQuery("FROM User WHERE username = :username AND password = :password", User.class)
				.setParameter("username", username)
				.setParameter("password", password).list().get(0);
		return user;
	}
	
	@Override
	public List<User> getUsers() {
		Session ses = HibernateUtil.getSession();
		List<User> users = ses.createQuery("From User", User.class).list();
		Log.info(users.get(0));
		return users;
	}

	@Override
	public void delete(User user) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.delete(user);
		tx.commit();
		ses.close();
	}

}
