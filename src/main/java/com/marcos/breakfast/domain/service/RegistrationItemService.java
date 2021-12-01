package com.marcos.breakfast.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.breakfast.api.assembler.ItemAssembler;

import com.marcos.breakfast.api.model.ItemModel;
import com.marcos.breakfast.domain.exception.BusinessException;
import com.marcos.breakfast.domain.model.Employee;
import com.marcos.breakfast.domain.model.Item;
import com.marcos.breakfast.domain.repository.ItemRepository;

@Service
public class RegistrationItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RegistrationEmployeeService employeeService;
	
	@Autowired
	private ItemAssembler itemAssembler;
	
	@Autowired
	private Utils utils;
	
	@Transactional
	public Page<ItemModel> findAll(Pageable pageable){
		Page<Item> list = itemRepository.listAll(pageable);
		return itemAssembler.toCollectionModel(list, pageable);
	}
	
	@Transactional
	public Optional<ItemModel> findById(Long itemId) {
		return itemRepository.searchById(itemId)
				.map(item -> itemAssembler.toModel(item));
	}
	
	@Transactional
	public Page<ItemModel> findByNameContaining(String name, Pageable pageable){
		Page<Item> list = itemRepository.searchNameContaining(name, pageable);
		return itemAssembler.toCollectionModel(list, pageable);
	}
	
	@Transactional
	public void remove(Long itemId) {
		itemRepository.removeById(itemId);
	}
	
	@Transactional
	public Item findLastItem() {
		return itemRepository.searchLastId();
	}
	
	@Transactional
	public ItemModel save(Item item) {
		Employee employee = employeeService.checkIfTheIdExists(item.getEmployee().getId());
		item.setEmployee(employee);
		
		/*Removendo espaços duplicados do nome*/
		var  text= utils.spaceRemoving(item.getName());
		item.setName(text);	
		
		Optional<Item> result = itemRepository.searchByName(item.getName());
		if(result.isPresent()) {
			throw new BusinessException("Este Item já está cadastrado");
		}
		itemRepository.create(item.getName(), item.getEmployee().getId());	
		return  itemAssembler.toModel(
				this.findLastItem());
	}
	
	@Transactional
	public ItemModel update (Item item) {
		Employee employee = employeeService.checkIfTheIdExists(item.getEmployee().getId());
		
		item.setEmployee(employee);
		
		/*Removendo espaços duplicados do nome*/
		var  text= utils.spaceRemoving(item.getName());
		item.setName(text);	
		
		Optional<Item> result = itemRepository.searchByName(item.getName().replaceAll("\\s+", " "));
		if((result.isPresent())) {
			boolean isEquals = result.get().equals(item);
			if(!isEquals) {
				throw new BusinessException("Este Item já está cadastrado");
			}
			itemRepository.update(item.getId(), item.getName(), item.getEmployee().getId());
			
			return itemAssembler.toModel(
					itemRepository
					.searchById(item.getId())
					.get());
		}
		itemRepository.update(item.getId(), item.getName(), item.getEmployee().getId());
		
		return itemAssembler.toModel(
				itemRepository
				.searchById(item.getId())
				.get());
	}
	
	
/*
 * Outra forma de gravar Item utilizando JPA
 */
//	@Transactional
//	public Item save(Item item) {
//		boolean existingName = itemRepository.searchByName(item.getName())
//				.stream()
//				.anyMatch(existingItemName -> !existingItemName.equals(item));
//		if(existingName) {
//			throw new BusinessException("Este item já está cadastrado");
//		}
//		return  itemRepository.save(item);
//	}*/
}
