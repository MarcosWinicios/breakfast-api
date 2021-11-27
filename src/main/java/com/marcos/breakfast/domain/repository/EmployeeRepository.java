package com.marcos.breakfast.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.breakfast.domain.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
