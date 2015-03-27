package com.finances.personal.core.model;

import java.util.List;

import com.finances.personal.filter.model.JournalEntryFilter;
import com.finances.personal.model.CategorizedMonetaryAmount;
import com.finances.personal.model.JournalEntryIdentifiableInfo;

public interface JournalKeeper {
	
	void insertEntry(JournalEntryIdentifiableInfo journalEntry, String createdBy);
	
	void deleteEntry(String id);
	
	List<JournalEntryIdentifiableInfo> queryEntries(JournalEntryFilter filter);
	
	List<CategorizedMonetaryAmount> queryCategorizedAmounts(JournalEntryFilter filter);
	
}