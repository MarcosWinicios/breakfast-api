package com.marcos.breakfast.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.breakfast.api.assembler.EmployeeAssembler;
import com.marcos.breakfast.api.assembler.ItemAssembler;
import com.marcos.breakfast.api.model.EmployeeModel;
import com.marcos.breakfast.api.model.ItemModel;
import com.marcos.breakfast.api.model.ItemResumeModel;
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
	
	public Employee checkIfTheIdExists(Long id) {
		return employeeRepository.searchById(id)
				.orElseThrow(() -> new BusinessException("Você está tentando atribuir um Item a um colaborador inexistente"));
	}
	
	
	@Transactional
	public void remove(Long employeeId) {
		employeeRepository.removeById(employeeId);
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
	
	@Transactional
	public Page<ItemResumeModel> findAllItemsByEmployeeId(Long employeeId, Pageable pageable){ 
		return itemAssembler.toResumeModelList(
				itemRepository.searchByEmployee(employeeId, pageable),
				pageable);
	}
	
	@Transactional
	public Employee findLastEmployee() {
		return employeeRepository.searchLastId();
	}
	
	@Transactional
	public EmployeeModel update (Employee employee) {
		
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
	public EmployeeModel save(Employee employee) {
		Optional<Employee> result = employeeRepository.searchByCpf(employee.getCpf());
		if(result.isPresent()) {
			throw new BusinessException("Já existe um colaborador cadastrado com esse cpf");
		}
		employeeRepository.create(employee.getName(), employee.getCpf());
		return employeeAssembler.toModel(employeeRepository.searchLastId());
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
