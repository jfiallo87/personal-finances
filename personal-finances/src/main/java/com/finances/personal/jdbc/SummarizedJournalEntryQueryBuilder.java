package com.finances.personal.jdbc;

import org.springframework.stereotype.Service;

@Service
public class SummarizedJournalEntryQueryBuilder extends
		AbstractJournalEntryQueryBuilder {

	@Override
	protected String getBaseSql() {
		return " select category, round(sum((value * type_modifier)), 2) total "
			 + " from journal_entry "
		   	 + " where 1 = 1 ";
	}

	@Override
	protected String getGrouping() {
		return " group by category ";
	}

}
