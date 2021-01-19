package com.cafe.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cafe.models.Order;
import com.cafe.service.ItemsService;
import com.cafe.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderController {
	
	private final static Logger Log = Logger.getLogger(OrderController.class);
	private final static String URL = "http://localhost:8080/BTAC-Cafe-Delivery/cafe/";

	//Get uses Order Id for retrieving the order
	public static void order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		Log.info("In order");
		if(req.getSession(false) != null) {
			Log.info("Session Exists");
			if(req.getMethod().equals("GET")) {
				Log.info("In Get");
				Order order = OrderService.getOrderById(Integer.parseInt(req.getParameter("id")));
				ObjectMapper om = new ObjectMapper();
				resp.getWriter().write(om.writeValueAsString(order));
				resp.setStatus(200);
			} else if(req.getMethod().equals("POST")) {
				Log.info("In Post");
				ObjectMapper om = new ObjectMapper();
				Order order = om.readValue(req.getReader(), Order.class);
				order.setUsername(req.getSession(false).getAttribute("username").toString());
				for(int x=0; x < order.getItems().size(); x++)
				{
					order.setTotal(order.getTotal() + order.getItems().get(x).getCost());
					order.getItems().get(x).setOrderId(order);
					ItemsService.addItems(order.getItems().get(x));
				}
				resp.setStatus(200);
			} else if(req.getMethod().equals("PUT")) {
				Log.info("In Put");
				ObjectMapper om = new ObjectMapper();
				Order order = om.readValue(req.getReader(), Order.class);
				OrderService.updateOrder(order);
			} else if(req.getMethod().equals("DELETE")) {
				Log.info("In Delete");
				ObjectMapper om = new ObjectMapper();
				Order order = om.readValue(req.getReader(), Order.class);
				OrderService.deleteOrder(order);
			} else {
				resp.setStatus(405);
			} 
		} else {
			resp.setStatus(401);
		}
	}
	
	//Get uses Order Id for retrieving the order
		public static void orderByUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
			Log.info("In order By User");
			if(req.getSession(false) != null) {
				if(req.getMethod().equals("GET")) {
					Log.info("In Get");
					List<Order> order = (List<Order>) OrderService.getOrderByUsername(req.getSession(false).getAttribute("username").toString());
					ObjectMapper om = new ObjectMapper();
					resp.setContentType("application/json");
					resp.getWriter().write(om.writeValueAsString(order));
					resp.setStatus(200);
				} else {
					resp.setStatus(405);
				} 
			} else {
				resp.setStatus(401);
			}
		}
	
	public static void orders(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {

		if(req.getSession(false) != null) {
			Log.info("In Orders");
			if(req.getMethod().equals("GET")) {
				Log.info("In Get");
				List<Order> orders = OrderService.getOrders();
				ObjectMapper om = new ObjectMapper();
				resp.setContentType("application/json");
				resp.getWriter().write(om.writeValueAsString(orders));
			} else {
				resp.setStatus(405);
			}
		} else {
			resp.setStatus(401);
		}
	}
}
