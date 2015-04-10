package com.finances.personal.filter.model;

import java.util.Collections;
import java.util.List;

import com.finances.personal.core.model.Lockable;
import com.finances.personal.model.Category;
import com.finances.personal.model.DateRange;
import com.finances.personal.model.MonetaryAmountRange;

public class JournalEntryFilter extends Lockable {
	
	public static final String CATEGORIES = "categories";
	public static final String AMOUNT_RANGE = "amountRange";
	public static final String DATE_RANGE = "dateRange";
	
	private List<Category> categories;
	private MonetaryAmountRange amountRange;
	private DateRange dateRange;
	
	public final List<Category> getCategories() {
		return (categories != null) ? Collections.unmodifiableList(categories) : null;
	}
	
	public final void setCategories(List<Category> categories) {
		lock(CATEGORIES);
		
		this.categories = categories;
	}

	public final MonetaryAmountRange getAmountRange() {
		return amountRange;
	}

	public final void setAmountRange(MonetaryAmountRange amountRange) {
		lock(AMOUNT_RANGE);
		
		this.amountRange = amountRange;
	}

	public final DateRange getDateRange() {
		return dateRange;
	}

	public final void setDateRange(DateRange dateRange) {
		lock(DATE_RANGE);
		
		this.dateRange = dateRange;
	}
	
}