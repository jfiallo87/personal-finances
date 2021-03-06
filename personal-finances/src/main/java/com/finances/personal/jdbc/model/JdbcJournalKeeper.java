package com.finances.personal.jdbc.model;

import static com.syntax.sql.jdbc.Statement.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.finances.personal.core.model.JournalKeeper;
import com.finances.personal.filter.model.JournalEntryFilter;
import com.finances.personal.model.CategorizedMonetaryAmount;
import com.finances.personal.model.JournalEntryIdentifiableInfo;

@Repository
public class JdbcJournalKeeper implements JournalKeeper {
	
	private final JdbcTemplate jdbcTemplate;
	private final DetailedJournalEntryQueryBuilder detailedJournalEntryQueryBuilder;
	private final SummarizedJournalEntryQueryBuilder summarizedJournalEntryQueryBuilder;
	private final RowMapper<JournalEntryIdentifiableInfo> journalEntryIdentifiableInfoRowMapper;
	private final RowMapper<CategorizedMonetaryAmount> summarizedJournalEntryRowMapper;
	
	private final JournalEntryTable journalEntryTable;
	
	@Autowired
	public JdbcJournalKeeper(JdbcTemplate jdbcTemplate,
								 DetailedJournalEntryQueryBuilder detailedJournalEntryQueryBuilder,
								 SummarizedJournalEntryQueryBuilder summarizedJournalEntryQueryBuilder,
								 RowMapper<JournalEntryIdentifiableInfo> journalEntryIdentifiableInfoRowMapper,
								 RowMapper<CategorizedMonetaryAmount> summarizedJournalEntryRowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.detailedJournalEntryQueryBuilder = detailedJournalEntryQueryBuilder;
		this.summarizedJournalEntryQueryBuilder = summarizedJournalEntryQueryBuilder;
		this.journalEntryIdentifiableInfoRowMapper = journalEntryIdentifiableInfoRowMapper;
		this.summarizedJournalEntryRowMapper = summarizedJournalEntryRowMapper;
		
		journalEntryTable = JournalEntryTable.describe();
	}
	
	@Override
	public void insertEntry(JournalEntryIdentifiableInfo journalEntry, String createdBy) {
		String sql = newStmt()
					.insert().into().table(journalEntryTable.tableName())
					.op()
					.field(journalEntryTable.id())
					.asWellAs().field(journalEntryTable.month())
					.asWellAs().field(journalEntryTable.day())
					.asWellAs().field(journalEntryTable.year())
					.asWellAs().field(journalEntryTable.category())
					.asWellAs().field(journalEntryTable.value())
					.asWellAs().field(journalEntryTable.type())
					.asWellAs().field(journalEntryTable.typeModifier())
					.asWellAs().field(journalEntryTable.createdBy())
					.asWellAs().field(journalEntryTable.createdDate())
					.cp()
					.values()
					.op()
					.parameter()
					.asWellAs().parameter()
					.asWellAs().parameter()
					.asWellAs().parameter()
					.asWellAs().parameter()
					.asWellAs().parameter()
					.asWellAs().parameter()
					.asWellAs().parameter()
					.asWellAs().parameter()
					.asWellAs().parameter()
					.cp()
					.toString();
		
		jdbcTemplate.update(sql, journalEntry.getId(),
								 journalEntry.getDate().getMonth().getValue(),
								 journalEntry.getDate().getDay(),
								 journalEntry.getDate().getYear(),
								 journalEntry.getCategory().getName(),
								 journalEntry.getValue(),
								 journalEntry.getType().toString(),
								 journalEntry.getType().getModifier(),
								 createdBy,
								 new Date());
	}

	@Override
	public void deleteEntry(String id) {
		String sql = newStmt()
					.delete().from().table(journalEntryTable.tableName())
					.where().field(journalEntryTable.id()).equal().parameter()
					.toString();
		
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<JournalEntryIdentifiableInfo> queryEntries(
			JournalEntryFilter filter) {
		JdbcQuery query = detailedJournalEntryQueryBuilder.buildQuery(filter);
		
		return jdbcTemplate.query(query.getSql(), journalEntryIdentifiableInfoRowMapper, query.getArgs());
	}

	@Override
	public List<CategorizedMonetaryAmount> queryCategorizedAmounts(
			JournalEntryFilter filter) {
		JdbcQuery query = summarizedJournalEntryQueryBuilder.buildQuery(filter);
		
		return jdbcTemplate.query(query.getSql(), summarizedJournalEntryRowMapper, query.getArgs());
	}
	
}