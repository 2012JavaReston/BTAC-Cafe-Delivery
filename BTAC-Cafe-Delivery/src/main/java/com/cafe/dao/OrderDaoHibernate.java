package com.cafe.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cafe.models.Order;
import com.cafe.util.HibernateUtil;

public class OrderDaoHibernate implements OrderDao {
	private final static Logger Log = Logger.getLogger(OrderDaoHibernate.class);

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(order);
		
		tx.commit();
		ses.close();
	}

	@Override
	public Order getOrderById(int id) {
		Session ses = HibernateUtil.getSession();
		Order order = ses.get(Order.class, id);
		ses.close();
		return order;
	}

	@Override
	public Order getOrderByUserId(int id) {
		Session ses = HibernateUtil.getSession();
		Order order = ses.createQuery("FROM Order WHERE userid = :id", Order.class)
				.setParameter("id", id).list().get(0);
		return order;
	}

	@Override
	public List<Order> getOrders() {
		Session ses = HibernateUtil.getSession();
		List<Order> orders = ses.createQuery("FROM Order", Order.class).list();
		return orders;
	}

	@Override
	public void deleteOrder(Order order) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.delete(order);
		tx.commit();
		ses.close();

	}

	@Override
	public void updateOrder(Order order) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(order);
		
		tx.commit();
		ses.close();

	}

}
