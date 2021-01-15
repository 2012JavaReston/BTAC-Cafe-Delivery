package com.cafe.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class RequestHelper {
	private final static Logger Log = Logger.getLogger(RequestHelper.class);
	
	public static void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String endpoint = req.getRequestURI();
		Log.info(endpoint);
		switch(endpoint) {
			case "/BTAC-Cafe-Delivery/":
				resp.sendRedirect("http://localhost:8080/BTAC-Cafe-Delivery/home");
			break;
			case "/BTAC-Cafe-Delivery/home":
				UserController.goHome(req, resp);
			break;
			case "/BTAC-Cafe-Delivery/api/user":
				UserController.createUser(req,resp);
				break;
			case "/BTAC-Cafe-Delivery/api/login":
				UserController.login(req,resp);
				break;
			case "/BTAC-Cafe-Delivery/api/item":
				ItemsController.item(req,resp);
				break;
			case "/BTAC-Cafe-Delivery/api/items":
				ItemsController.items(req,resp);
				break;
			case "/BTAC-Cafe-Delivery/api/order":
				OrderController.order(req,resp);
				break;
			case "/BTAC-Cafe-Delivery/api/orders":
				OrderController.orders(req,resp);
				break;
			case "/BTAC-Cafe-Delivery/api/orderbyuser":
				OrderController.orderByUser(req,resp);
				break;
			case "/BTAC-Cafe-Delivery/api/getallusers":
				UserController.getUsers(req,resp);
				break;
			default:
//				RequestDispatcher redis = req.getRequestDispatcher("/hello");
//				redis.forward(req, resp);
		}
	}
}
