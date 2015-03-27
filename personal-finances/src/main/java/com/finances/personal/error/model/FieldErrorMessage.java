package com.finances.personal.error.model;

public enum FieldErrorMessage {
	
	IS_LOCKED("is locked!"),
	CANNOT_BE_EMPTY("cannot be empty!"),
	IS_REQUIRED("is requred!"),
	IS_OUT_OF_RANGE("is out of range!");
	
	private String label;
	
	private FieldErrorMessage(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
	
}