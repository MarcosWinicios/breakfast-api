package com.marcos.breakfast.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemInput {
	
	@Valid
	@NotNull
	private EmployeeIdInput employee;	
	
	@Valid
	@NotBlank
	private String itemName;

	public EmployeeIdInput getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeIdInput employee) {
		this.employee = employee;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
