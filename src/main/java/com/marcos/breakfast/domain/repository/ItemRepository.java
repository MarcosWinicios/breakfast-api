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
	
	@Query(value = "SELECT * FROM tb_item WHERE id = :id", nativeQuery = true)
	public Optional<Item> searchById(Long id);
	
	@Query(value = "INSERT INTO tb_item (name, employee_id) VALUES (:item.getName(), :employeeId)", nativeQuery = true)
	public Item create(Item item, Long employeeId);
	
	@Query(value = "SELECT * FROM tb_item WHERE name = :name", nativeQuery = true)
	public Optional<Item> searchByName(String name);
	
	@Query(value = "SELECT * FROM tb_item WHERE name LIKE %:name%", nativeQuery = true)
	public Page<Item> searchNameContaining(String name, Pageable pageable);

//	public void removeById(Long id);
}
