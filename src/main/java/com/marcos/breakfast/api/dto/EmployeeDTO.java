package com.marcos.breakfast.api.dto;

import java.util.List;

public class EmployeeDTO {
	private Long id;
	private String name;
	private String cpf;
	private List<ItemResumeDTO> items;
	
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
	public List<ItemResumeDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemResumeDTO> items) {
		this.items = items;
	}
}
