package com.finances.personal.error;

import com.finances.personal.error.model.FieldErrorMessage;

public class ErrorUtils {
	
	private ErrorUtils() {}
	
	public static String buildErrorMessage(FieldErrorMessage message, String field, String... fieldStack) {
		String errorMessage = field;
		
		if (fieldStack != null) {
			errorMessage += buildFieldStack(fieldStack);
		}
		
		errorMessage += " " + message;
		
		return errorMessage;
	}
	
	private static String buildFieldStack(String... fieldStack) {
		String stack = "";
		
		for (String field : fieldStack) {
			stack += "." + field;
		}
		
		return stack;
	}
	
}