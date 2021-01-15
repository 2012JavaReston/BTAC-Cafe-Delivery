package com.cafe.service;

import java.util.List;

import com.cafe.dao.ItemDao;
import com.cafe.dao.ItemDaoHibernate;
import com.cafe.models.Items;

public class ItemsService {
	public static ItemDao iDao = new ItemDaoHibernate();
	
	public static void addItems(Items item) {
		iDao.addItem(item);
	}
	
	public static Items getItem(int id) {
		return iDao.getItem(id);
	}
	
	public static Items getItemByName(String name) {
		return iDao.getItemByName(name);
	}
	
	public static List<Items> getItems() {
		return iDao.getItems();
	}
	
	public static void removeItem(Items item) {
		iDao.removeItem(item);
	}
}
