package com.marcos.breakfast.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marcos.breakfast.domain.model.Item;

public interface ItemRepository  extends JpaRepository<Item, Long>{

	@Query(value = "SELECT * FROM tb_item", nativeQuery = true)
	public Page<Item> listAll (Pageable pageable);
	
	@Query(value = "SELECT * FROM tb_item WHERE id = :itemId", nativeQuery = true)
	public Optional<Item> searchById(Long itemId);
	
	@Query(value = "INSERT INTO tb_item (name, employee_id) VALUES (:item.getName(), :employeeId)", nativeQuery = true)
	public Item create(Item item, Long employeeId);
	
	@Query(value = "SELECT * FROM tb_item WHERE name = :itemName", nativeQuery = true)
	public Optional<Item> searchByName(String itemName);
	
	@Query(value = "SELECT * FROM tb_item WHERE name LIKE %:itemName%", nativeQuery = true)
	public Page<Item> searchNameContaining(String itemName, Pageable pageable);
	
	@Query(value = "DELETE FROM tb_item WHERE id = :itemId", nativeQuery = true)
	public void removeById(Long itemId);
	
	@Query(value = "SELECT COUNT(1) FROM tb_item WHERE id = :itemId", nativeQuery = true)
	public Long verifyId(Long itemId);
	
	
}
