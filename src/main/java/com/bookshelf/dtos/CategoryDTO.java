package com.bookshelf.dtos;

import java.io.Serializable;

import com.bookshelf.domain.Category;

public class CategoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String none;
	private String description;
	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryDTO(Category obj) {
		super();
		this.id = obj.getId();
		this.none = obj.getName();
		this.description = obj.getDescription();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNone() {
		return none;
	}
	public void setNone(String none) {
		this.none = none;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
