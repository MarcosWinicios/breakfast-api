package com.marcos.breakfast.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.breakfast.api.assembler.EmployeeAssembler;
import com.marcos.breakfast.api.assembler.ItemAssembler;
import com.marcos.breakfast.api.dto.EmployeeDTO;
import com.marcos.breakfast.api.dto.ItemDTO;
import com.marcos.breakfast.api.dto.ItemResumeDTO;
import com.marcos.breakfast.domain.exception.BusinessException;
import com.marcos.breakfast.domain.model.Employee;
import com.marcos.breakfast.domain.repository.EmployeeRepository;
import com.marcos.breakfast.domain.repository.ItemRepository;

@Service
public class RegistrationEmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeAssembler employeeAssembler;
	
	@Autowired
	private ItemAssembler itemAssembler;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private Utils utils;
	
	public Employee checkIfTheIdExists(Long id) {
		return employeeRepository.searchById(id)
				.orElseThrow(() -> new BusinessException("Você está tentando atribuir um Item a um colaborador inexistente"));
	}
	
	
	@Transactional
	public void remove(Long employeeId) {
		employeeRepository.removeById(employeeId);
	}
	
	@Transactional
	public Page<EmployeeDTO> findAll(Pageable pageable){
		return employeeAssembler.toCollection(
				employeeRepository.listAll(pageable), 
				pageable) ;
	}
	
	@Transactional
	public Optional<EmployeeDTO> findById(Long id){
		return employeeRepository.searchById(id)
				.map(employee -> employeeAssembler.toModel(employee));
	}
	
	@Transactional
	public Optional<EmployeeDTO> findByCpf(String cpf){
		return employeeRepository.searchByCpf(cpf)
				.map(employee -> employeeAssembler.toModel(employee));
	}
	
	@Transactional
	public Page<EmployeeDTO> findByNameContaing(String name, Pageable pageable){
		return employeeAssembler.toCollection(
				employeeRepository.searchNameContaining(name, pageable), 
				pageable);
	}
	
	@Transactional
	public Page<ItemResumeDTO> findAllItemsByEmployeeId(Long employeeId, Pageable pageable){ 
		return itemAssembler.toResumeModelList(
				itemRepository.searchByEmployee(employeeId, pageable),
				pageable);
	}
	
	@Transactional
	public Employee findLastEmployee() {
		return employeeRepository.searchLastId();
	}
	
	@Transactional
	public EmployeeDTO update (Employee employee) {
		employee = this.removingSpaceText(employee);
		
		Optional<Employee> result = employeeRepository.searchByCpf(employee.getCpf());
		if((result.isPresent())) {
			boolean isEquals = result.get().equals(employee);
			if(!isEquals) {
				throw new BusinessException("Já existe um colaborador cadastrado com esse cpf");
			}
			employeeRepository.update(employee.getId(), employee.getName(), employee.getCpf());
			return	employeeAssembler.toModel(
					employeeRepository
					.searchById(employee.getId())
					.get());
		}
		employeeRepository.update(employee.getId(), employee.getName(), employee.getCpf());
		
		return	employeeAssembler.toModel(
				employeeRepository
				.searchById(employee.getId())
				.get());
	}
	
	@Transactional
	public EmployeeDTO save(Employee employee) {
		employee = this.removingSpaceText(employee);
		
		System.out.println("\nVALOR DO CPF ANTES DE SALVAR: " + employee.toString());
		
		Optional<Employee> result = employeeRepository.searchByCpf(employee.getCpf());
		if(result.isPresent()) {
			throw new BusinessException("Já existe um colaborador cadastrado com esse cpf");
		}
		employeeRepository.create(employee.getName(), employee.getCpf());
		return employeeAssembler.toModel(employeeRepository.searchLastId());
	}
	
	private Employee removingSpaceText(Employee employee) {
		employee.setName(utils.spaceRemoving(employee.getName()));
		System.out.println("\n\n------------------\nCPF: |"+ employee.getName() +"|\n\n------------------");

		System.out.println("\n\n------------------\nCPF: |"+ utils.spaceRemoving(employee.getCpf())+"|\n\n------------------");
		employee.setCpf(this.cpfValidation(employee.getCpf()));
		System.out.println("\n\nCPF DEPOIS DE REMOVER ESPAÇOS:" + employee.toString() + "\n\n");
		this.cpfValidation(employee.getCpf());
		
		return employee;
	}
	
	private String cpfValidation(String cpf) {
		cpf = cpf.trim().replaceAll("\\s+", "");
		
		System.out.println("\n\n------------------\nCPF: |"+ cpf +"|\n\n------------------");
		
		if(!(cpf.length() == 11)) {
			throw new BusinessException("O cpf deve ter 11 dígitos sem espaços");
		}
		
		return cpf;
	}
	
	/*
	 * Outra forma de salvar/atualizar Colaboradores utilizando o JPA
	@Transactional
	public  Employee save(Employee employee) {
		
		boolean existingCpf = employeeRepository.searchByCpf(employee.getCpf())
				.stream()
				.anyMatch(existingEmployee -> !existingEmployee.equals(employee));
		if(existingCpf) {
			throw new BusinessException("Já existe um cliente cadastrado com este CPF");
		}
		return employeeRepository.save(employee);
	}
	*/
	
}
