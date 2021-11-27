package com.marcos.breakfast.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		boolean existingCpf = employeeRepository.findByCpf(employee.getCpf())
				.stream()
				.anyMatch(existingEmployee -> !existingEmployee.equals(employee));
		if(existingCpf) {
			throw new BusinessExcepetion("Já existe um cliente cadastrado com este CPF");
		}
		
		return employeeRepository.save(employee);
	}
	
	@Transactional
	public void remove(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}
}
