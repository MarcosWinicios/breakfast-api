package com.marcos.breakfast.api.dto;

import java.text.ParseException;
import java.util.List;

import com.marcos.breakfast.domain.service.Utils;

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
	public String getCpf() throws ParseException {
		this.cpf = Utils.cpfMask(cpf);
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
