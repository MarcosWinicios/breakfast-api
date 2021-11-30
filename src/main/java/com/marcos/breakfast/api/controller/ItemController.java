package com.marcos.breakfast.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.breakfast.api.assembler.ItemAssembler;
import com.marcos.breakfast.api.model.ItemModel;
import com.marcos.breakfast.api.model.input.ItemInput;
import com.marcos.breakfast.domain.model.Item;
import com.marcos.breakfast.domain.repository.ItemRepository;
import com.marcos.breakfast.domain.service.RegistrationItemService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/items")
public class ItemController {
	
	@Autowired
	private RegistrationItemService itemService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemAssembler itemAssembler;
	
	@GetMapping
	public ResponseEntity<Page<ItemModel>> findAll(Pageable pageable){
		Page<ItemModel> list = itemService.findAll(pageable);		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{itemId}")
	public ResponseEntity<ItemModel> findById(@PathVariable Long itemId){		 
		return itemService.findById(itemId)
				.map(item -> ResponseEntity.ok(item))
				.orElse(ResponseEntity.notFound().build());		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ItemModel> save(@Valid @RequestBody ItemInput item){
		System.out.println("ITEM A SER INSERIDO : " + item.toString());
		
		Item newItem = itemAssembler.toEntity(item); 
		return ResponseEntity.ok(itemService.save(newItem));
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Page<ItemModel>> findByNameContaing(@PathVariable String name, Pageable pageable){
		Page<ItemModel> result = itemService.findByNameContaining(name, pageable);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{itemId}")
	public ResponseEntity<Void> remove (@PathVariable Long itemId){
		if(!(itemRepository.verifyId(itemId) > 0)) {
			return ResponseEntity.notFound().build();
		}
		itemService.remove(itemId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{itemId}")
	public ResponseEntity<ItemModel> update(@Valid @PathVariable Long itemId, @RequestBody Item item){
		if(!(itemRepository.verifyId(itemId) > 0)) {
			return ResponseEntity.notFound().build();
		}
		
		item.setId(itemId);
		System.out.println("ITEM A SER INSERIDO PELO UPDATE: " + item.toString());
		return ResponseEntity.ok(itemService.update(item));
	}
	
	
//	@PutMapping("/{itemId}")
//	public ResponseEntity<ItemModel> update(@Valid @PathVariable Long itemId, @RequestBody ItemModel itemModel){
//		if(!(itemRepository.verifyId(itemId) > 0)) {
//			return ResponseEntity.notFound().build();
//		}
//		Item newItem = itemAssembler.toEntity(itemModel);
//		newItem.setId(itemId);
//		return ResponseEntity.ok(itemService.save(newItem));
//	}
}
