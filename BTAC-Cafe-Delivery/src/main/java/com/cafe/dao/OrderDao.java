package com.cafe.dao;

import java.util.List;

import com.cafe.models.Order;

public interface OrderDao {
	
	public void addOrder(Order order);
	
	public void updateOrder(Order order);
	
	public Order getOrderById(int id);
	
	public Order getOrderByUserId(int id);
	
	public List<Order> getOrders();
	
	public void deleteOrder(Order order);
}
