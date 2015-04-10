package com.finances.personal.core.model;

import java.util.HashSet;
import java.util.Set;

import com.finances.personal.error.ErrorUtils;
import com.finances.personal.error.model.FieldErrorMessage;

public abstract class Lockable {
	
	private final Set<String> lockedFields = new HashSet<String>();
	
	protected final void lock(String field) {
		if (lockedFields.contains(field)) {
			throw new UnsupportedOperationException(ErrorUtils.buildErrorMessage(FieldErrorMessage.IS_LOCKED, field));
		}
		
		lockedFields.add(field);
	}

}