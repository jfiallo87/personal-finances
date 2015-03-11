package com.finances.personal.model;

import com.finances.personal.core.ValueObject;

public class MonetaryAmount extends ValueObject {
	
	public static final String VALUE = "value";
	public static final String TYPE = "type";
	
	private float value;
	private MonetaryAmountType type;
	
	public final float getValue() {
		return value;
	}
	
	public final void setValue(float value) {
		lock(VALUE);
		
		this.value = value;
	}

	public final MonetaryAmountType getType() {
		return type;
	}

	public final void setType(MonetaryAmountType type) {
		lock(TYPE);
		
		this.type = type;
	}

}