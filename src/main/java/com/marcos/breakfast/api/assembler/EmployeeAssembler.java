package com.marcos.breakfast.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.marcos.breakfast.api.model.EmployeeModel;
import com.marcos.breakfast.api.model.input.EmployeeInput;
import com.marcos.breakfast.domain.model.Employee;

@Component
public class EmployeeAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public EmployeeModel toModel(Employee employee) {
		return mapper.map(employee, EmployeeModel.class);
	}
	
	public Page<EmployeeModel> toCollection(Page<Employee> list, Pageable pageable){
		return list.map(employee -> this.toModel(employee));
	}
	
	public Employee toEntity(EmployeeInput employee) {
		return mapper.map(employee, Employee.class);
	}
}
