package com.marcos.breakfast.domain.service;

import org.springframework.stereotype.Component;

@Component
public class Utils {
		
	public String spaceRemoving(String text) {
		return text.trim().replaceAll("\\s+", " ");
	}
}
