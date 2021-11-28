package com.marcos.breakfast.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.marcos.breakfast.api.model.ItemModel;
import com.marcos.breakfast.api.model.ItemResumeModel;
import com.marcos.breakfast.api.model.input.ItemInput;
import com.marcos.breakfast.domain.model.Item;

@Component
public class ItemAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public ItemModel toModel(Item item) {
		return mapper.map(item, ItemModel.class);
	}
	
	public Page<ItemModel> toCollectionModel(Page<Item> list, Pageable pageable){ 
		return list.map(item -> this.toModel(item));		
	}
	
	public Item toEntity(ItemInput itemInput) {
		return mapper.map(itemInput, Item.class);
	}
	
	public ItemResumeModel toResumeModel(Item item) {
		return mapper.map(item, ItemResumeModel.class);
	}
	
	public Page<ItemResumeModel> toResumeModelList(Page<Item> list, Pageable pageable) { 
		return list.map(item -> this.toResumeModel(item));
	}
}
