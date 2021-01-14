package com.cafe.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.cafe.models.User;
import com.cafe.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {
	
	private final static Logger Log = Logger.getLogger(UserController.class);
	private final static String URL = "http://localhost:8080/BTAC-Cafe-Delivery/cafe/";

	public static void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Log.info(req.getMethod().toString());
		if(req.getMethod().equals("POST")) {
//			JSONObject json = new JSONObject();
			Log.info("In Post for Create User");
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(req.getReader(), com.cafe.models.User.class);
			Log.info(user.getFirstName());
			UserService.createUser(user);
//			Log.info(user.getFirstName() + user.getLastName());
//			Log.info(user.getUsername());
		} else {
			Log.info("Not post");
//			resp.setStatus(405);
//			resp.sendRedirect(URL + "login");
		}
	}
	
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		if(req.getMethod().equals("POST")) {
			Log.info("Logging in");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			User user = UserService.verifyUser(username, password);
			HttpSession sesh = req.getSession();
			sesh.setAttribute("user_id", user.getId());

			resp.sendRedirect(URL + "home");
		} else {
//			resp.setStatus(405);
//			resp.sendRedirect(URL + "home");
		}
	}

	public static void goHome(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(req.getSession(false) != null) {
			Log.info("Going to Home Page");
			
		} else {
//			Log.info("Going to Login page");
//			resp.sendRedirect(URL + "login.html");
		}
	}
}
