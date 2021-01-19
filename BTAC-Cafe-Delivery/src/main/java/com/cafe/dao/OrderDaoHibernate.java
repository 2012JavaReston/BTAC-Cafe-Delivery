package com.cafe.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cafe.models.Order;
import com.cafe.util.HibernateUtil;

/**
 *Dao for the items object utilizing hibernate to store the objects
 */
public class OrderDaoHibernate implements OrderDao {
	private final static Logger Log = Logger.getLogger(OrderDaoHibernate.class);
	
	//Adds a given order to the database
	@Override
	public void addOrder(Order order) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(order);
		
		tx.commit();
		ses.close();
	}
	
	//Retrieves an order with the given id from the database. Returns null if nothing is found
	@Override
	public Order getOrderById(int id) {
		Session ses = HibernateUtil.getSession();
		Order order = ses.get(Order.class, id);
		ses.close();
		return order;
	}

	//Retrieves all orders of the given userId. Returns null if nothing is found
	@Override
	public List<Order> getOrderByUserId(int id) {
		Session ses = HibernateUtil.getSession();
		List<Order> order = ses.createQuery("FROM Order WHERE userid = :id", Order.class)
				.setParameter("id", id).list();
		return order;
	}
	
	//Retrieves all orders. Returns null if nothing is found.
	@Override
	public List<Order> getOrders() {
		Session ses = HibernateUtil.getSession();
		List<Order> orders = ses.createQuery("FROM Order", Order.class).list();
		return orders;
	}

	//Deletes the given order from the database
	@Override
	public void deleteOrder(Order order) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.delete(order);
		tx.commit();
		ses.close();

	}
	
	//Updates the given order from the database.
	@Override
	public void updateOrder(Order order) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(order);
		
		tx.commit();
		ses.close();

	}

}
