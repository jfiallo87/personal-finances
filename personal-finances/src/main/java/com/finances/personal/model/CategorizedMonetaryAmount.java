package com.finances.personal.model;

public class CategorizedMonetaryAmount extends MonetaryAmount {
	
	public static final String CATEGORY = "category";
	
	private Category category;
	
	public final Category getCategory() {
		return category;
	}

	public final void setCategory(Category category) {
		lock(CATEGORY);
		
		this.category = category;
	}
	
}