package com.marcos.breakfast.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.breakfast.api.assembler.EmployeeAssembler;
import com.marcos.breakfast.api.model.EmployeeModel;
import com.marcos.breakfast.domain.exception.BusinessExcepetion;
import com.marcos.breakfast.domain.model.Employee;
import com.marcos.breakfast.domain.repository.EmployeeRepository;

@Service
public class RegistrationEmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeAssembler employeeAssembler;
	
	public Employee checkIfTheIdExists(Long id) {
		return employeeRepository.searchById(id)
				.orElseThrow(() -> new BusinessExcepetion("Você está tentando atribuir um Item a um colaborador inexistente"));
	}
	
	@Transactional
	public  EmployeeModel save(Employee employee) {
		
		Optional<Employee> result = employeeRepository.searchByCpf(employee.getCpf());
		if(result.isPresent()) {
			boolean isEquals = result.get().equals(employee);
			if(!isEquals) {
				throw new BusinessExcepetion("Já existe um colaborador cadastrado com este CPF");
			}
//			return employeeRepository.update(employee.getId(), employee.getName(), employee.getCpf());
			return employeeAssembler.toModel(
					employeeRepository.save(employee)); //Método de update aqui
		}
		return employeeAssembler.toModel(
				employeeRepository.save(employee));
	}
	
	
	@Transactional
	public void remove(Long employeeId) {
//		employeeRepository.removeById(employeeId);
		employeeRepository.deleteById(employeeId);
	}
	
	@Transactional
	public Page<EmployeeModel> findAll(Pageable pageable){
		return employeeAssembler.toCollection(
				employeeRepository.listAll(pageable), 
				pageable) ;
	}
	
	@Transactional
	public Optional<EmployeeModel> findById(Long id){
		return employeeRepository.searchById(id)
				.map(employee -> employeeAssembler.toModel(employee));
	}
	
	@Transactional
	public Optional<EmployeeModel> findByCpf(String cpf){
		return employeeRepository.searchByCpf(cpf)
				.map(employee -> employeeAssembler.toModel(employee));
	}
	
	@Transactional
	public Page<EmployeeModel> findByNameContaing(String name, Pageable pageable){
		return employeeAssembler.toCollection(
				employeeRepository.searchNameContaining(name, pageable), 
				pageable);
	}
	
	/*
	@Transactional
	public  Employee save(Employee employee) {
		
		boolean existingCpf = employeeRepository.searchByCpf(employee.getCpf())
				.stream()
				.anyMatch(existingEmployee -> !existingEmployee.equals(employee));
		if(existingCpf) {
			throw new BusinessExcepetion("Já existe um cliente cadastrado com este CPF");
		}
		return employeeRepository.save(employee);
	}
	*/
	
}
