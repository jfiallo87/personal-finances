package com.finances.personal.jdbc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class JournalEntryQueryBuilder extends AbstractJournalEntryQueryBuilder {
	
	protected String getBaseSql() {
		String sql = " SELECT * FROM journal_entry "
				   + " where 1 = 1 ";
		
		return sql;
	}

	@Override
	protected String getGrouping() {
		return StringUtils.EMPTY;
	}

}