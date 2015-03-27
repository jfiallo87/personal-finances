package com.finances.personal.application.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finances.personal.core.model.JournalKeeper;
import com.finances.personal.filter.model.JournalEntryFilter;
import com.finances.personal.model.CategorizedMonetaryAmount;
import com.finances.personal.model.CategorizedSummary;
import com.finances.personal.model.JournalEntry;
import com.finances.personal.model.JournalEntryIdentifiableInfo;
import com.finances.personal.model.JournalEntryInfo;
import com.finances.personal.model.JournalSummary;

@Service
public class Journal {
	
	private JournalKeeper keeper;
	
	@Autowired
	public Journal(JournalKeeper keeper) {
		this.keeper = keeper;
	}
	
	@Transactional
	public void createNewEntry(JournalEntryInfo info, String createdBy) {
		JournalEntry newEntry = new JournalEntry(info);
		
		keeper.insertEntry(newEntry.getIdentifiableInfo(), createdBy);
	}
	
	@Transactional
	public void deleteEntry(String id) {
		keeper.deleteEntry(id);
	}
	
	public List<JournalEntryIdentifiableInfo> queryEntries(JournalEntryFilter filter) {
		return keeper.queryEntries(filter);
	}
	
	public CategorizedSummary summarize(JournalEntryFilter filter) {
		List<CategorizedMonetaryAmount> amounts = keeper.queryCategorizedAmounts(filter);
		
		JournalSummary journalSummary = new JournalSummary(amounts);
		
		return journalSummary.getCategorizedSummary();
	}

}