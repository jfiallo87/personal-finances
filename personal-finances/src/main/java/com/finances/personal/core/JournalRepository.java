package com.finances.personal.core;

import java.util.List;

import com.finances.personal.model.CategorizedMonetaryAmount;
import com.finances.personal.model.JournalEntryFilter;
import com.finances.personal.model.JournalEntryIdentifiableInfo;

public interface JournalRepository {
	
	void insertEntry(JournalEntryIdentifiableInfo journalEntry, String createdBy);
	
	void deleteEntry(String id);
	
	List<JournalEntryIdentifiableInfo> queryEntries(JournalEntryFilter filter);
	
	List<CategorizedMonetaryAmount> queryCategorizedAmounts(JournalEntryFilter filter);
	
}