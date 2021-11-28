package com.marcos.breakfast.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marcos.breakfast.domain.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Optional<Employee> findByCpf(String cpf);
	
	@Query(value = "SELECT COUNT(1) FROM tb_employee WHERE id = :employeeId", nativeQuery = true)
	public Long verifyId(Long employeeId);
	
	@Query(value = "SELECT COUNT(1) FROM tb_employee WHERE id = :cpf", nativeQuery = true)
	public Long verifyCpf(String cpf);
	
	@Query(value = "INSERT INTO tb_employee (name, cpf) VALUES(:name, :cpf)", nativeQuery = true)
	public Employee create(String name, String cpf);
	
//	public Employee create(@Param("name") String name, @Param("cpf") String cpf);

	@Query(value = "SELECT * FROM tb_employee e INNER JOIN tb_item i ON e.id = i.employee_id", nativeQuery = true)
//	@Query(value = "SELECT * FROM tb_employee", nativeQuery = true)
	public Page<Employee> listAll(Pageable pageable);
	
	@Query(value = "SELECT * FROM tb_employee WHERE id = :employeeId", nativeQuery = true)
	public Optional<Employee> searchById(Long employeeId);
	
	@Query(value = "SELECT * FROM tb_employee WHERE cpf = :cpf", nativeQuery = true)
	public Optional<Employee> searchByCpf(String cpf);
	
	@Query(value = "SELECT * FROM tb_employee WHERE name LIKE %:name%", nativeQuery = true)
	public Page<Employee> searchNameContaining (String name, Pageable pageable);
	
	@Query(value = "DELETE FROM tb_employee WHERE id = :employeeId", nativeQuery = true)
	public void removeById(Long employeeId);
	
	@Query(value = "UPDATE tb_employee SET name = :employeeName, cpf = :cpf WHERE id = :employeeId", nativeQuery = true)
	public Employee update(Long employeeId, String employeeName, String cpf);
}
