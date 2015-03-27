package com.finances.personal.controller;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.security.Principal;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finances.personal.application.model.Journal;
import com.finances.personal.application.model.JournalEntryFilterBuilder;
import com.finances.personal.filter.model.JournalEntryFilter;
import com.finances.personal.model.JournalEntryIdentifiableInfo;
import com.finances.personal.model.JournalEntryInfo;
import com.finances.personal.model.MonetaryAmountType;

@RestController
@RequestMapping(value="/journal")
public class JournalController {
	
	private final Journal journal;
	private final JournalEntryFilterBuilder filterBuilder;
	
	@Autowired
	public JournalController(Journal journal, JournalEntryFilterBuilder filterBuilder) {
		this.journal = journal;
		this.filterBuilder = filterBuilder;
	}
	
	@RequestMapping(method=GET, produces=APPLICATION_JSON_VALUE)
	public List<JournalEntryIdentifiableInfo> query(@RequestParam(value="fromType", required=false) MonetaryAmountType fromType,
													@RequestParam(value="fromValue", required=false) Float fromValue,
													@RequestParam(value="toType", required=false) MonetaryAmountType toType,
													@RequestParam(value="toValue", required=false) Float toValue,
													@RequestParam(value="fromMonth", required=false) Month fromMonth,
													@RequestParam(value="fromDay", required=false) Short fromDay,
													@RequestParam(value="fromYear", required=false) Short fromYear,
													@RequestParam(value="toMonth", required=false) Month toMonth,
													@RequestParam(value="toDay", required=false) Short toDay,
													@RequestParam(value="toYear", required=false) Short toYear,
													@RequestParam(value="categoryNames", required=false) List<String> categoryNames) {
		JournalEntryFilter filter = filterBuilder.buildFilter(fromType, fromValue,
															  toType, toValue,
															  fromMonth, fromDay, fromYear,
															  toMonth, toDay, toYear,
															  categoryNames);
		
		return journal.queryEntries(filter);
	}
	
	@RequestMapping(method=POST, consumes=APPLICATION_JSON_VALUE)
	public void post(@RequestBody JournalEntryInfo info,
					 Principal principal) {
		journal.createNewEntry(info, principal.getName());
	}
	
}