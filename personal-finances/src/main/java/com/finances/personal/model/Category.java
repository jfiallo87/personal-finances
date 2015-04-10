package com.finances.personal.model;

import com.finances.personal.core.model.Lockable;

public class Category extends Lockable {
	
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