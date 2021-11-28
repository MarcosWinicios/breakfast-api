package com.marcos.breakfast.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.breakfast.domain.exception.BusinessExcepetion;
import com.marcos.breakfast.domain.model.Employee;
import com.marcos.breakfast.domain.repository.EmployeeRepository;

@Service
public class RegistrationEmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Transactional
	public  Employee save(Employee employee) {
		
		boolean existingCpf = employeeRepository.searchByCpf(employee.getCpf())
				.stream()
				.anyMatch(existingEmployee -> !existingEmployee.equals(employee));
		if(existingCpf) {
			throw new BusinessExcepetion("Já existe um cliente cadastrado com este CPF");
		}
		return employeeRepository.create(employee.getName(), employee.getCpf());

//		return employeeRepository.save(employee);
	}
	
	@Transactional
	public void remove(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}
	
	@Transactional
	public Page<Employee> findAll(Pageable pageable){
		return employeeRepository.listAll(pageable);
	}
	
	@Transactional
	public Optional<Employee> findById(Long id){
		return employeeRepository.searchById(id);
	}
	
	@Transactional
	public Optional<Employee> findByCpf(String cpf){
		return employeeRepository.searchByCpf(cpf);
	}
	
	@Transactional
	public Page<Employee> findByNameContaing(String name, Pageable pageable){
		return employeeRepository.searchNameContaining(name, pageable);
	}
}
