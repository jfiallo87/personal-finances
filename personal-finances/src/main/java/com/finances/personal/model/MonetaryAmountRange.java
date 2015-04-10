package com.finances.personal.model;

import com.finances.personal.core.model.Lockable;

public class MonetaryAmountRange extends Lockable {
	
	public static final String FROM_AMOUNT = "fromAmount";
	public static final String TO_AMOUNT = "toAmount";
	
	private MonetaryAmount fromAmount;
	private MonetaryAmount toAmount;
	
	public final MonetaryAmount getFromAmount() {
		return fromAmount;
	}
	
	public final void setFromAmount(MonetaryAmount fromAmount) {
		lock(FROM_AMOUNT);
		
		this.fromAmount = fromAmount;
	}

	public final MonetaryAmount getToAmount() {
		return toAmount;
	}

	public final void setToAmount(MonetaryAmount toAmount) {
		lock(TO_AMOUNT);
		
		this.toAmount = toAmount;
	}
	
}