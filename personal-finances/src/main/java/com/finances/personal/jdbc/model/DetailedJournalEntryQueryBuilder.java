package com.finances.personal.jdbc.model;

import static com.syntax.sql.jdbc.Statement.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DetailedJournalEntryQueryBuilder extends JournalEntryQueryBuilder {
	
	protected String getBaseSql() {
		String sql = newStmt().select().all().from().table(journalEntryTable.tableName())
					.where().oneEqualOne()
					.toString();
		
		return sql;
	}

	@Override
	protected String getGrouping() {
		return StringUtils.EMPTY;
	}

}