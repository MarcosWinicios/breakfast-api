package com.marcos.breakfast.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marcos.breakfast.domain.model.Item;
import com.marcos.breakfast.domain.repository.ItemRepository;

@Service
public class RegistrationItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Page<Item> findAll(Pageable pageable){
		Page<Item> list = itemRepository.listAll(pageable);
		
		return list;
	}
	
	public Optional<Item> findById(Long itemId) {
		return itemRepository.searchById(itemId);
	}
}
