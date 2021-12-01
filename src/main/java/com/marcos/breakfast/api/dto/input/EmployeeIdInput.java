package com.marcos.breakfast.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EmployeeIdInput {
	
	@Valid
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
