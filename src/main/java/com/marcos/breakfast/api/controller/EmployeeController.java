package com.marcos.breakfast.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee save(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/{employeeId}")
	public ResponseEntity<Employee> update(@PathVariable Long employeeId, @RequestBody Employee employee ) {
		if(!employeeRepository.existsById(employeeId)) {
			return ResponseEntity.notFound().build();
		}
		employee.setId(employeeId);
		employee = employeeRepository.save(employee);
		return ResponseEntity.ok(employee);
	}

}
