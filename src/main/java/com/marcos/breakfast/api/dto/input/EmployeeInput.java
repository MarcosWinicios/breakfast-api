package com.marcos.breakfast.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EmployeeInput {
	
	@NotNull
	@Valid
	private String name;
	
	@NotNull
	@Valid
	private String cpf;

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
