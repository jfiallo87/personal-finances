package com.finances.personal.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.personal.application.model.Journal;

@RestController
@RequestMapping(value="/journal/{id}")
public class JournalEntryController {
	
	private final Journal journal;
	
	@Autowired
	public JournalEntryController(Journal journal) {
		this.journal = journal;
	}
	
	@RequestMapping(method=DELETE)
	public void delete(@PathVariable(value="id") String id) {
		journal.deleteEntry(id);
	}
	
}