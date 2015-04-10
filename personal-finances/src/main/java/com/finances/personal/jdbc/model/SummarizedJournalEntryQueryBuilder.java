package com.finances.personal.jdbc.model;

import static com.syntax.sql.jdbc.Function.*;
import static com.syntax.sql.jdbc.Statement.*;

import org.springframework.stereotype.Service;

@Service
public class SummarizedJournalEntryQueryBuilder extends
		JournalEntryQueryBuilder {

	@Override
	protected String getBaseSql() {
		String valueTimesTypeModifier = journalEntryTable.value().concat("*").concat(journalEntryTable.typeModifier());
		String total = "total";
		
		String sql = newStmt()
					 .select().field(journalEntryTable.category()).asWellAs().function(round().with(sum().with(valueTimesTypeModifier), 2)).as(total)
					 .from().table(journalEntryTable.tableName())
					 .where().oneEqualOne()
					 .toString();
		
		return sql;
	}

	@Override
	protected String getGrouping() {
		return " group by category ";
	}

}
