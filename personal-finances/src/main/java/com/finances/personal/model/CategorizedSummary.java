package com.finances.personal.model;

import java.util.Collections;
import java.util.List;

import com.finances.personal.core.ValueObject;

public class CategorizedSummary extends ValueObject {
	
	public static final String TOTALS = "totals";
	public static final String CATEGORIZED_AMOUNTS = "categorizedAmounts";
	
	private MonetaryAmountTotals totals;
	private List<CategorizedMonetaryAmount> categorizedAmounts;
	
	public final MonetaryAmountTotals getTotals() {
		return totals;
	}
	
	public final void setTotals(MonetaryAmountTotals totals) {
		lock(TOTALS);
		
		this.totals = totals;
	}

	public List<CategorizedMonetaryAmount> getCategorizedAmounts() {
		return (categorizedAmounts != null) ? Collections.unmodifiableList(categorizedAmounts) : null;
	}

	public void setCategorizedAmounts(List<CategorizedMonetaryAmount> categorizedAmounts) {
		lock(CATEGORIZED_AMOUNTS);
		
		this.categorizedAmounts = categorizedAmounts;
	}

}