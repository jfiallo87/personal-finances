package com.finances.personal.jdbc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.finances.personal.model.Category;
import com.finances.personal.model.Date;
import com.finances.personal.model.JournalEntryIdentifiableInfo;
import com.finances.personal.model.MonetaryAmountType;

@Service
public class JournalEntryIdentifiableInfoRowMapper implements
		RowMapper<JournalEntryIdentifiableInfo> {

	@Override
	public JournalEntryIdentifiableInfo mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Category category = new Category();
		category.setName(rs.getString("category"));
		
		Date date = new Date();
		date.setDay(rs.getShort("day"));
		date.setMonth(Month.of(rs.getShort("month")));
		date.setYear(rs.getShort("year"));
		
		JournalEntryIdentifiableInfo journalEntry = new JournalEntryIdentifiableInfo();
		journalEntry.setCategory(category);
		journalEntry.setDate(date);
		journalEntry.setId(rs.getString("id"));
		journalEntry.setType(MonetaryAmountType.valueOf(rs.getString("type")));
		journalEntry.setValue(rs.getFloat("value"));
		
		return journalEntry;
	}

}
