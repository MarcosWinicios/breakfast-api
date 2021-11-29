package com.marcos.breakfast.api.model;

import java.util.List;

public class EmployeeModel {
	private Long id;
	private String name;
	private String cpf;
	private List<ItemResumeModel> items;
	
	public Long getId() {
		return id;
	}
	public void setId(Long idEmployee) {
		this.id = idEmployee;
	}
	public String getName() {
		return name;
	}
	public void setNameEmployee(String nameEmployee) {
		this.name = nameEmployee;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<ItemResumeModel> getItems() {
		return items;
	}
	public void setItems(List<ItemResumeModel> items) {
		this.items = items;
	}
}
