package com.cafe.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	public static void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String endpoint = req.getRequestURI();
		
		switch(endpoint) {
			case "/BTAC-Cafe-Delivery/home":
				UserController.goHome(req, resp);
			break;
			case "/BTAC-Cafe-Delivery/api/user":
				UserController.createUser(req,resp);
				break;
			case "/BTAC-Cafe-Delivery/api/login":
				UserController.login(req,resp);
				break;
			default:
				RequestDispatcher redis = req.getRequestDispatcher("/hello");
				redis.forward(req, resp);
				break;
		}
	}
}
