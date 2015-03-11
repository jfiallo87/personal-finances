package com.finances.personal.model;

import java.util.UUID;

import org.apache.commons.lang3.Range;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import com.finances.personal.util.ErrorUtils;

public class JournalEntry {
	
	private JournalEntryIdentifiableInfo identifiableInfo;
	
	public JournalEntry(JournalEntryInfo info) {
		if (info == null) {
			throw new IllegalArgumentException();
		}
		
		identifiableInfo = new JournalEntryIdentifiableInfo();
		
		identifiableInfo.setId(UUID.randomUUID().toString());
		identifiableInfo.setCategory(info.getCategory());
		identifiableInfo.setDate(info.getDate());
		identifiableInfo.setType(info.getType());
		identifiableInfo.setValue(info.getValue());
		
		validate();
	}
	
	public JournalEntryIdentifiableInfo getIdentifiableInfo() {
		return identifiableInfo;
	}
	
	private void validate() {
		validateId();
		validateCategory();
		validateDate();
		validateType();
		validateValue();
	}
	
	private void validateId() {
		if (!StringUtils.hasText(identifiableInfo.getId())) {
			throw new IllegalStateException(ErrorUtils.buildErrorMessage(FieldErrorMessage.CANNOT_BE_EMPTY, JournalEntryIdentifiableInfo.ID));
		}
	}

	private void validateCategory() {
		if (identifiableInfo.getCategory() == null) {
			throw new IllegalStateException(ErrorUtils.buildErrorMessage(FieldErrorMessage.IS_REQUIRED, JournalEntryIdentifiableInfo.CATEGORY));
		}
		
		if (!StringUtils.hasText(identifiableInfo.getCategory().getName())) {
			throw new IllegalStateException(ErrorUtils.buildErrorMessage(FieldErrorMessage.CANNOT_BE_EMPTY, JournalEntryIdentifiableInfo.CATEGORY, Category.NAME));
		}
	}
	
	private void validateDate() {
		if (identifiableInfo.getDate() == null) {
			throw new IllegalStateException(ErrorUtils.buildErrorMessage(FieldErrorMessage.IS_REQUIRED, JournalEntryIdentifiableInfo.DATE));
		}
		
		if (identifiableInfo.getDate().getMonth() == null) {
			throw new IllegalStateException(ErrorUtils.buildErrorMessage(FieldErrorMessage.IS_REQUIRED, JournalEntryIdentifiableInfo.DATE, Date.MONTH));
		}
		
		Range<Short> validDay = Range.between(NumberUtils.convertNumberToTargetClass(1, Short.class), NumberUtils.convertNumberToTargetClass(identifiableInfo.getDate().getMonth().maxLength(), Short.class));
		
		if (!validDay.contains(identifiableInfo.getDate().getDay())) {
			throw new IllegalStateException(ErrorUtils.buildErrorMessage(FieldErrorMessage.IS_OUT_OF_RANGE, JournalEntryIdentifiableInfo.DATE, Date.DAY));
		}
		
		if (identifiableInfo.getDate().getYear() <= 0) {
			throw new IllegalStateException(ErrorUtils.buildErrorMessage(FieldErrorMessage.IS_OUT_OF_RANGE, JournalEntryIdentifiableInfo.DATE, Date.YEAR));
		}
	}
	
	private void validateType() {
		if (identifiableInfo.getType() == null) {
			throw new IllegalStateException(ErrorUtils.buildErrorMessage(FieldErrorMessage.IS_REQUIRED, JournalEntryIdentifiableInfo.TYPE));
		}
	}
	
	private void validateValue() {
		if (identifiableInfo.getValue() <= 0) {
			throw new IllegalStateException(ErrorUtils.buildErrorMessage(FieldErrorMessage.IS_OUT_OF_RANGE, JournalEntryIdentifiableInfo.VALUE));
		}
	}
	
}