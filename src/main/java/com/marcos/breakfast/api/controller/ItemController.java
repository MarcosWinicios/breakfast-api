package com.marcos.breakfast.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
