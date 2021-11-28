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
		if(!(employeeRepository.verifyId(item.getEmployee().getId()) > 0)) {
			throw new BusinessExcepetion("Você está tentando atribuir um Item a um colaborador inexistente");
		}
		Optional<Item> result = itemRepository.searchByName(item.getName());
		
		if(result.isPresent()) {
			if(!(result.get().equals(item))) {
				throw new BusinessExcepetion("Este item já está cadastrado");
			}
//			return itemRepository.update(item.getId(), item.getName(), item.getEmployee().getId());
			return  itemRepository.save(item);//Método de update aqui
		}
		return  itemRepository.save(item);
	}
	
	@Transactional
	public Page<Item> findByNameContaining(String name, Pageable pageable){
		return itemRepository.searchNameContaining(name, pageable);
	}
	
	@Transactional
	public void remove(Long itemId) {
		itemRepository.removeById(itemId);
		
//		itemRepository.deleteById(itemId);	
	}
	
//	@Transactional
//	public Item save(Item item) {
//		boolean existingName = itemRepository.searchByName(item.getName())
//				.stream()
//				.anyMatch(existingItemName -> !existingItemName.equals(item));
//		if(existingName) {
//			throw new BusinessExcepetion("Este item já está cadastrado");
//		}
//		return  itemRepository.save(item);
//	}
}
