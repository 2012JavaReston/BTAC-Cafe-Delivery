package com.cafe.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cafe.models.Items;
import com.cafe.service.ItemsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemsController {
	
	private final static Logger Log = Logger.getLogger(ItemsController.class);
	private final static String URL = "http://localhost:8080/BTAC-Cafe-Delivery/cafe/";
	
	public static void item(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		Log.info("In Item");
		if(req.getSession(false) != null) {
			Log.info("Session Exists");
			if(req.getMethod().equals("GET")) {
				Log.info("In Get");
				Items item = new Items();
				if(req.getParameter("name") != null) {
					item = ItemsService.getItemByName(req.getParameter("name"));
				} else if(req.getParameter("id") != null) {
					item = ItemsService.getItem(Integer.parseInt(req.getParameter("id")));
				}
				ObjectMapper om = new ObjectMapper();
				resp.setContentType("application/json");
				resp.getWriter().write(om.writeValueAsString(item));
				resp.setStatus(200);
			} else if(req.getMethod().equals("POST")) {
				Log.info("In Post");
				ObjectMapper om = new ObjectMapper();
				Items item = om.readValue(req.getReader(), Items.class);
				ItemsService.addItems(item);
				resp.setStatus(200);
			} else if(req.getMethod().equals("DELETE")) {
				Log.info("In Delete");} else {
				resp.setStatus(405);
			}
		} else {
			resp.setStatus(401);
		}
	}
	
	public static void items(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		Log.info("In items");
		if(req.getSession(false) != null) {
			Log.info("Session Exists");
			if(req.getMethod().equals("GET")) {
				Log.info("In Get");
				List<Items> items = ItemsService.getItems();
				ObjectMapper om = new ObjectMapper();
				resp.setContentType("application/json");
				resp.getWriter().write(om.writeValueAsString(items));
				resp.setStatus(200);
			} else {
				resp.setStatus(405);
			}
		} else {
			resp.setStatus(401);
		}
	}
}
