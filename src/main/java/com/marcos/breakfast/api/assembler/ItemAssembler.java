package com.marcos.breakfast.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.marcos.breakfast.api.dto.ItemDTO;
import com.marcos.breakfast.api.dto.ItemResumeDTO;
import com.marcos.breakfast.api.dto.input.ItemInput;
import com.marcos.breakfast.domain.model.Item;

@Component
public class ItemAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public ItemDTO toModel(Item item) {
		return mapper.map(item, ItemDTO.class);
	}
	
	public Page<ItemDTO> toCollectionModel(Page<Item> list, Pageable pageable){ 
		return list.map(item -> this.toModel(item));		
	}
	
	public Item toEntity(ItemInput itemInput) {
		return mapper.map(itemInput, Item.class);
	}
	
	public ItemResumeDTO toResumeModel(Item item) {
		return mapper.map(item, ItemResumeDTO.class);
	}
	
	public Page<ItemResumeDTO> toResumeModelList(Page<Item> list, Pageable pageable) { 
		return list.map(item -> this.toResumeModel(item));
	}
}
