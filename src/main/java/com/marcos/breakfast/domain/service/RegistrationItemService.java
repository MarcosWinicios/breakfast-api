package com.marcos.breakfast.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marcos.breakfast.domain.exception.BusinessExcepetion;
import com.marcos.breakfast.domain.model.Item;
import com.marcos.breakfast.domain.repository.EmployeeRepository;
import com.marcos.breakfast.domain.repository.ItemRepository;

@Service
public class RegistrationItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Transactional
	public Page<Item> findAll(Pageable pageable){
		Page<Item> list = itemRepository.listAll(pageable);
		return list;
	}
	
	@Transactional
	public Optional<Item> findById(Long itemId) {
		return itemRepository.searchById(itemId);
	}
	
	@Transactional
	public Item save(Item item) {
		boolean existingName = itemRepository.searchByName(item.getName())
				.stream()
				.anyMatch(existingItemName -> !existingItemName.equals(item));
		if(existingName) {
			throw new BusinessExcepetion("Este item já está cadastrado");
		}

		Item savadedItem = itemRepository.save(item);
		
		return savadedItem;
//		return itemRepository.create(item, item.getEmployee().getId());
	}
	
	@Transactional
	public Page<Item> findByNameContaining(String name, Pageable pageable){
		return itemRepository.searchNameContaining(name, pageable);
	}
	
	@Transactional
	public void remove(Long itemId) {
		
//		itemRepository.removeById(itemId);
		try {
			itemRepository.deleteById(itemId);
		} catch (Exception e) {
			throw new RuntimeException(e);
//			throw new BusinessExcepetion("Não foi possível excluir");
		}	
	}
	
	@Transactional
	public Item update(Item item) { 
		System.out.println(item.toString());
		if((itemRepository.verifyName(item.getName()) > 0)) {
			
		}
		
		return itemRepository.save(item);
	}
}
