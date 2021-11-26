package com.marcos.breakfast.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.breakfast.domain.model.Employee;


@RestController
public class EmployeeController {
	
	
	
	@GetMapping("/employees")
	public List<Employee> listar() {
		var employee1 = new Employee(1L, "Marcos Winicios Pereira	", "11111111111");
		var employee2 = new Employee(2L, "João Alves", "22222222222");

		var employee3 = new Employee(3L, "Bill Gates", "33333333333");

		var employee4 = new Employee(4L, "Steve Jobs", "44444444444");

		
		return Arrays.asList(employee1, employee2, employee3, employee4);
	}
	
	@GetMapping("/employee")
	public Employee findById(Long id) {
		
		return new Employee(1L, "Marcos Winicios", "11111111111");
	}
	
}
