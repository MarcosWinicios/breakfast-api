package com.marcos.breakfast.api.model;

import com.marcos.breakfast.domain.model.Item;

public class ItemModel {
	
	private Long id;
	private String itemName;
	private String employeeName;
	
	
	public ItemModel(Item item) {
		this.id = item.getId();
		this.itemName = item.getName();
		this.employeeName = item.getEmployee().getName();
	}
	
	public ItemModel(Long id, String itemName, String employeeName) {
		this.id = id;
		this.itemName = itemName;
		this.employeeName = employeeName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
