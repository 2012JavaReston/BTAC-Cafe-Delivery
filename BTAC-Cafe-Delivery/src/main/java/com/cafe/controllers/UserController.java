package com.cafe.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cafe.models.User;
import com.cafe.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {
	
	private final static Logger Log = Logger.getLogger(UserController.class);
	private final static String URL = "http://localhost:8080/BTAC-Cafe-Delivery/";

	public static void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Log.info(req.getMethod().toString());
		if(req.getMethod().equals("POST")) {
			Log.info("In Post for Create User");
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(req.getReader(), com.cafe.models.User.class);
			Log.info(user.getFirstName());
			UserService.createUser(user);
			resp.setStatus(201);
		} else {
			resp.setStatus(403);
			Log.info("Not post");
		}
	}
	
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		if(req.getMethod().equals("POST")) {
			Log.info("Logging in");
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(req.getReader(), com.cafe.models.User.class);
			String username = user.getUsername();
			String password = user.getPassword();
			Log.info("Username " + username);
			Log.info("Password " + password);
			user = UserService.verifyUser(username, password);
			HttpSession sesh = req.getSession();
			sesh.setAttribute("user_id", user.getId());
			sesh.setAttribute("username", user.getUsername());
			Log.info(user.getFirstName());
			resp.setStatus(200);
		} else {
			resp.setStatus(401);
		}
	}

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(req.getSession(false) != null) {
			req.getSession().invalidate();
			resp.sendRedirect(URL + "cafe/home");
			resp.setStatus(200);
		} else {
			resp.setStatus(405);
		}
	}
	
	public static void goHome(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(req.getSession(false) != null) {
			Log.info("Going to Home Page");
			RequestDispatcher redis = req.getRequestDispatcher("/portal.html");
			redis.forward(req, resp);
			resp.setStatus(200);
		} else {
			resp.setStatus(405);
		}
	}
	
	public static void getUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<User> users = UserService.getUsers();
		Log.info(users.toString());
		ObjectMapper om = new ObjectMapper();
		resp.setContentType("application/json");
		resp.getWriter().write(om.writeValueAsString(users));
	}
}
