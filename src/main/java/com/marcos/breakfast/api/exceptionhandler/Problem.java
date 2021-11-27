package com.marcos.breakfast.api.exceptionhandler;

import java.time.LocalDateTime;

public class Problem {
	private Integer status;
	private LocalDateTime dateHour;
	private String title;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDateTime getDateHour() {
		return dateHour;
	}
	public void setDateHour(LocalDateTime dateHour) {
		this.dateHour = dateHour;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
