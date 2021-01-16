package com.cafe.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cafe_items")
public class Items {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "item_name", nullable = false)
	private String itemName;
	
	@Column(name = "item_cost", nullable = false)
	private double cost;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_foreign_key")
	private Order orderId;
	
	public Items() {
		super();
	}

	public Items(int id, String itemName, double cost) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.cost = cost;
	}
	
	public Items(String itemName, double cost) {
		super();
		this.itemName = itemName;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
