package com.marcos.breakfast.api.model;

public class EmployeeModel {
	private Long id;
	private String name;
	private String cpf;
	
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
}
