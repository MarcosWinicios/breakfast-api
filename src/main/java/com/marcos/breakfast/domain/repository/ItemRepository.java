package com.marcos.breakfast.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.marcos.breakfast.domain.model.Item;

public interface ItemRepository  extends JpaRepository<Item, Long>{
	
	@Query(value = "SELECT id, name, employee_id FROM tb_item", nativeQuery = true)
	public Page<Item> listAll (Pageable pageable);
	
	@Query(value = "SELECT * FROM tb_item WHERE id = :itemId", nativeQuery = true)
	public Optional<Item> searchById(Long itemId);
	
	@Query(value = "SELECT * FROM tb_item WHERE name ~* :itemName", nativeQuery = true)
	public Optional<Item> searchByName(String itemName);
	
	@Query(value = "SELECT * FROM tb_item WHERE name LIKE %:itemName%", nativeQuery = true)
	public Page<Item> searchNameContaining(String itemName, Pageable pageable);
	
	@Query(value = "SELECT COUNT(1) FROM tb_item WHERE id = :itemId", nativeQuery = true)
	public Long verifyId(Long itemId);
	
	@Query(value = "SELECT COUNT(1) FROM tb_item WHERE name = :itemName", nativeQuery = true)
	public Long verifyName(String itemName);
	
	
	@Query(value = "SELECT * FROM  tb_item WHERE employee_id = :employeeId", nativeQuery = true)
	public Page<Item> searchByEmployee(Long employeeId, Pageable pageable);
	
	@Query(value = "SELECT * FROM tb_item WHERE id = (SELECT MAX(id) FROM tb_item);", nativeQuery = true)
	public Item searchLastId();
	
	@Modifying
	@Query(value = "INSERT INTO tb_item (name, employee_id) VALUES (?1, ?2)", nativeQuery = true)
	public Integer create(String name, Long employeeId);
	
	@Modifying
	@Query(value = "UPDATE tb_item SET name = ?2, employee_id = ?3 WHERE id = ?1", nativeQuery = true)
	public  Integer update(Long itemId, String itemName, Long employeeId);
	
	@Modifying
	@Query(value = "DELETE FROM tb_item WHERE id = ?1", nativeQuery = true)
	public Integer removeById(Long itemId);
}
