package com.marcos.breakfast.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.breakfast.domain.model.Employee;
import com.marcos.breakfast.domain.repository.EmployeeRepository;


@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping
	public ResponseEntity<Page<Employee>> findAll(Pageable page) {
		Page<Employee> list = employeeRepository.findAll(page);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> findById(@PathVariable Long employeeId) {
		return employeeRepository.findById(employeeId)
				.map(employee -> ResponseEntity.ok(employee))
				.orElse(ResponseEntity.notFound().build());
		
//		Optional<Employee> result = employeeRepository.findById(employeeId);
//		if(result.isPresent()) {			
//			return ResponseEntity.ok(result.get());
//		}
//		return ResponseEntity.notFound().build();
	}
}
