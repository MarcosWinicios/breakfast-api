package com.marcos.breakfast.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.breakfast.api.dto.EmployeeDTO;
import com.marcos.breakfast.api.dto.ItemResumeDTO;
import com.marcos.breakfast.domain.model.Employee;
import com.marcos.breakfast.domain.repository.EmployeeRepository;
import com.marcos.breakfast.domain.service.RegistrationEmployeeService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RegistrationEmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<Page<EmployeeDTO>> findAll(Pageable page) {
		Page<EmployeeDTO> list = employeeService.findAll(page);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> findById(@PathVariable Long employeeId) {
		return employeeService.findById(employeeId)
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
	public EmployeeDTO save(@Valid @RequestBody Employee employee) {
		return employeeService.save(employee);
	}
	
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> update(@Valid @PathVariable Long employeeId, @RequestBody Employee employee ) {
		if(!(employeeRepository.verifyId(employeeId) > 0)) {
			return ResponseEntity.notFound().build();
		}
		employee.setId(employeeId);
		return ResponseEntity.ok(employeeService.update(employee));
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> remove(@PathVariable Long employeeId){
		if(!employeeRepository.existsById(employeeId)) {
			return ResponseEntity.notFound().build();
		}
		employeeService.remove(employeeId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<EmployeeDTO> findByCpf(@PathVariable String cpf){
		return employeeService.findByCpf(cpf)
				.map(employee -> ResponseEntity.ok(employee))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Page<EmployeeDTO>> findByNameContaing(@PathVariable String name, Pageable pageable){
		return ResponseEntity.ok(
				employeeService.findByNameContaing(name, pageable));
	}
	
	@GetMapping("/{employeeId}/items")
	public ResponseEntity<Page<ItemResumeDTO>> findAllItems(@PathVariable Long employeeId, Pageable pageable){
		if(!(employeeRepository.verifyId(employeeId) > 0)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(
				employeeService.findAllItemsByEmployeeId(employeeId, pageable));
	}
}
