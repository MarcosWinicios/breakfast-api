package com.marcos.breakfast.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marcos.breakfast.domain.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByCpf(String cpf);
	
	@Query(value = "SELECT COUNT(1) FROM tb_employee WHERE id = :employeeId", nativeQuery = true)
	public Long verifyId(Long employeeId);
}
