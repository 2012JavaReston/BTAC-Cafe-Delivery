package com.cafe.service;

import java.util.List;

import com.cafe.dao.ItemDao;
import com.cafe.dao.ItemDaoHibernate;
import com.cafe.models.Items;

public class ItemsService {
	public static ItemDao iDao = new ItemDaoHibernate();
	
	
	//Calls the addItem functionality of ItemDao
	public static void addItems(Items item) {
		iDao.addItem(item);
	}
	
	//Calls the getItems functionality of ItemDao and returns an item of a certain item id
	public static Items getItem(int id) {
		return iDao.getItem(id);
	}
	
	//Calls the getItems functionality of ItemDao and returns all items of a certain name
	public static Items getItemByName(String name) {
		return iDao.getItemByName(name);
	}
	
	//Calls the getAllItems functionality of ItemDao and returns all items
	public static List<Items> getItems() {
		return iDao.getItems();
	}
	
	//Calls the remove item functionality of ItemDao
	public static void removeItem(Items item) {
		iDao.removeItem(item);
	}
}
