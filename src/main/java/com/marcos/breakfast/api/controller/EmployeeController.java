package com.marcos.breakfast.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.breakfast.domain.model.Employee;


@RestController
public class EmployeeController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping("/employees")
	public List<Employee> listar() {
		
		return manager.createQuery("from employee", Employee.class).getResultList();
	}

	
}
