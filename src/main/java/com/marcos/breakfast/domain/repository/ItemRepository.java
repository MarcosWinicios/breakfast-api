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
	
//	public Optional<Item> create(Item item);
//	
//	public void removeById(Long id);
}
