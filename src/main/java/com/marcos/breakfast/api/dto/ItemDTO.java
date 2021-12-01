package com.marcos.breakfast.api.dto;

import com.marcos.breakfast.api.dto.input.EmployeeIdInput;

public class ItemDTO {
	
	private Long id;
	private String itemName;
	private EmployeeIdInput employee;
	
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
	public EmployeeIdInput getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeIdInput employee) {
		this.employee = employee;
	}
}
