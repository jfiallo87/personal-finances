package com.finances.personal.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finances.personal.core.JournalRepository;
import com.finances.personal.model.CategorizedMonetaryAmount;
import com.finances.personal.model.CategorizedSummary;
import com.finances.personal.model.JournalEntry;
import com.finances.personal.model.JournalEntryFilter;
import com.finances.personal.model.JournalEntryIdentifiableInfo;
import com.finances.personal.model.JournalEntryInfo;
import com.finances.personal.model.JournalSummary;

@Service
public class Journal {
	
	private JournalRepository repository;
	
	@Autowired
	public Journal(JournalRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public void createNewEntry(JournalEntryInfo info, String createdBy) {
		JournalEntry newEntry = new JournalEntry(info);
		
		repository.insertEntry(newEntry.getIdentifiableInfo(), createdBy);
	}
	
	@Transactional
	public void deleteEntry(String id) {
		repository.deleteEntry(id);
	}
	
	public List<JournalEntryIdentifiableInfo> queryEntries(JournalEntryFilter filter) {
		return repository.queryEntries(filter);
	}
	
	public CategorizedSummary summarize(JournalEntryFilter filter) {
		List<CategorizedMonetaryAmount> amounts = repository.queryCategorizedAmounts(filter);
		
		JournalSummary journalSummary = new JournalSummary(amounts);
		
		return journalSummary.getCategorizedSummary();
	}

}