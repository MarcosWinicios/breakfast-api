package com.marcos.breakfast.domain.model;

public class Employee {
	private Long id;
	private String name;
	private String cpf;
	
	public Employee() {}
	
	public Employee(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
	
	public Employee(Long id, String name, String cpf) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
}
 