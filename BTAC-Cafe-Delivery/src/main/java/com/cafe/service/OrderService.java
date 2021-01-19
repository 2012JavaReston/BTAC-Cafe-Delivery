package com.cafe.service;

import java.util.List;

import com.cafe.dao.OrderDao;
import com.cafe.dao.OrderDaoHibernate;
import com.cafe.models.Order;

public class OrderService {
	
	public static OrderDao oDao = new OrderDaoHibernate();
	
	public static void addOrder(Order order) {
		oDao.addOrder(order);
	}
	
	public static Order getOrderById(int id) {
		return oDao.getOrderById(id);
	}
	
//	public static List<Order> getOrderByUserId(int id) {
//		return oDao.getOrderByUserId(id);
//	}
	
	public static List<Order> getOrderByUsername(String username) {
		return oDao.getOrderByUsername(username);
	} 
	
	public static List<Order> getOrders() {
		return oDao.getOrders();
	}
	
	public static void updateOrder(Order order) {
		oDao.updateOrder(order);
	}
	
	public static void deleteOrder(Order order) {
		oDao.deleteOrder(order);
	}
}
