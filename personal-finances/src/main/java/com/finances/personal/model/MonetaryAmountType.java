package com.finances.personal.model;

public enum MonetaryAmountType {
	
	CREDIT(1),
	DEBIT(-1);
	
	int modifier;
	
	MonetaryAmountType(int modifier) {
		this.modifier = modifier;
	}
	
	public static MonetaryAmountType fromAmount(float amount) {
		return (amount > 0) ? CREDIT : DEBIT;
	}
	
	public int getModifier() {
		return modifier;
	}
	
	public float eval(float amount) {
		return amount * modifier;
	}
	
}