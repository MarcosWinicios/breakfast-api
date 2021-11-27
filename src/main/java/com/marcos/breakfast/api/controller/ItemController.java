package com.marcos.breakfast.api.controller;

import java.awt.ItemSelectable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.breakfast.domain.model.Item;
import com.marcos.breakfast.domain.service.RegistrationItemService;


@RestController
@RequestMapping(value = "/items")
public class ItemController {
	
	@Autowired
	private RegistrationItemService itemService;
	
	@GetMapping
	public ResponseEntity<Page<Item>> findAll(Pageable pageable){
		Page<Item> list = itemService.findAll(pageable);		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{itemId}")
	public ResponseEntity<Item> findById(@PathVariable Long itemId){		 
		return itemService.findById(itemId)
				.map(item -> ResponseEntity.ok(item))
				.orElse(ResponseEntity.notFound().build());		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Item> save(@RequestBody Item item){
		System.out.println("ITEM A SER INSERIDO: " + item.toString());
		
		return ResponseEntity.ok(new Item());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Page<Item>> findByNameContaing(@PathVariable String name, Pageable pageable){
		Page<Item> result = itemService.findByNameContaining(name, pageable);
		return ResponseEntity.ok(result);
	}
}
