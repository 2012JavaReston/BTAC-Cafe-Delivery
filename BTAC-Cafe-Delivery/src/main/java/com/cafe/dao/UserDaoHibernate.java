package com.cafe.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cafe.models.User;
import com.cafe.util.HibernateUtil;

public class UserDaoHibernate implements UserDao {

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
		Session ses = HibernateUtil.getSession();
		User user = (User) ses.createQuery("FROM cafe_users WHERE username = :username AND password = :password")
				.setParameter("username", username)
				.setParameter("password", password)
				.list().get(0);
		return user;
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
