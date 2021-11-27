package com.marcos.breakfast.api.exceptionhandler;

public class Field {

	private String name;
	private String mesage;
	
	public Field(String name, String mesage) {
		this.name = name;
		this.mesage = mesage;
	}
	
	public String getName() {
		return name;
	}
	public String getMesage() {
		return mesage;
	}	
}
