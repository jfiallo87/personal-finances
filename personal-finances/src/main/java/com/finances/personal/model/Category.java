package com.finances.personal.model;

import com.finances.personal.core.model.ValueObject;

public class Category extends ValueObject {
	
	public static final String NAME = "name";
	
	private String name;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		lock(NAME);
		
		this.name = name;
	}
	
}