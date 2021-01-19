package com.cafe.dao;

import java.util.List;

import com.cafe.models.Items;

public interface ItemDao {

	public void addItem(Items item);
	
	public Items getItem(int id);
	
	public Items getItemByName(String name);
	
	public List<Items> getItems();
	
	public void removeItem(Items item);
}
