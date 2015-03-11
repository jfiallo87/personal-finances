package com.finances.personal.core;

import java.util.HashSet;
import java.util.Set;

import com.finances.personal.model.FieldErrorMessage;
import com.finances.personal.util.ErrorUtils;

public abstract class ValueObject {
	
	private final Set<String> lockedFields = new HashSet<String>();
	
	protected final void lock(String field) {
		if (lockedFields.contains(field)) {
			throw new UnsupportedOperationException(ErrorUtils.buildErrorMessage(FieldErrorMessage.IS_LOCKED, field));
		}
		
		lockedFields.add(field);
	}

}