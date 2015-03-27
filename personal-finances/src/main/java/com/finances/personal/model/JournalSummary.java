package com.finances.personal.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.finances.personal.core.FloatUtils;

public class JournalSummary {
	
	private CategorizedSummary categorizedSummary;
	
	public JournalSummary(List<CategorizedMonetaryAmount> amounts) {
		if (CollectionUtils.isEmpty(amounts)) {
			amounts = new ArrayList<CategorizedMonetaryAmount>();
		}
		
		float credits = calculateCredits(amounts);
		float debits = calculateDebits(amounts);
		
		float balance = credits - debits;
		
		MonetaryAmountTotals totals = new MonetaryAmountTotals();
		
		totals.setCredits(credits);
		totals.setDebits(debits);
		totals.setBalance(balance);
		
		categorizedSummary = new CategorizedSummary();
		
		categorizedSummary.setCategorizedAmounts(amounts);
		categorizedSummary.setTotals(totals);
	}
	
	public CategorizedSummary getCategorizedSummary() {
		return categorizedSummary;
	}
	
	private float calculateCredits(List<CategorizedMonetaryAmount> amounts) {
		return calculate(MonetaryAmountType.CREDIT, amounts);
	}
	
	private float calculateDebits(List<CategorizedMonetaryAmount> amounts) {
		return calculate(MonetaryAmountType.DEBIT, amounts);
	}
	
	private float calculate(MonetaryAmountType type, List<CategorizedMonetaryAmount> amounts) {
		float sum = 0;
		
		CategorizedMonetaryAmount[] amountsByType = amounts.stream().filter(amount -> type.equals(amount.getType())).toArray(CategorizedMonetaryAmount[]::new);
		
		for (CategorizedMonetaryAmount amount : amountsByType) {
			sum += amount.getValue();
		}
		
		return FloatUtils.round(sum);
	}

}