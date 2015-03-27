package com.finances.personal.jdbc.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.finances.personal.core.model.JournalKeeper;
import com.finances.personal.filter.model.JournalEntryFilter;
import com.finances.personal.jdbc.JournalEntryQueryBuilder;
import com.finances.personal.jdbc.SummarizedJournalEntryQueryBuilder;
import com.finances.personal.model.CategorizedMonetaryAmount;
import com.finances.personal.model.JournalEntryIdentifiableInfo;

@Repository
public class JdbcJournalKeeper implements JournalKeeper {
	
	private final JdbcTemplate jdbcTemplate;
	private final JournalEntryQueryBuilder journalEntryQueryBuilder;
	private final SummarizedJournalEntryQueryBuilder summarizedJournalEntryQueryBuilder;
	private final RowMapper<JournalEntryIdentifiableInfo> journalEntryIdentifiableInfoRowMapper;
	private final RowMapper<CategorizedMonetaryAmount> summarizedJournalEntryRowMapper;
	
	@Autowired
	public JdbcJournalKeeper(JdbcTemplate jdbcTemplate,
								 JournalEntryQueryBuilder journalEntryQueryBuilder,
								 SummarizedJournalEntryQueryBuilder summarizedJournalEntryQueryBuilder,
								 RowMapper<JournalEntryIdentifiableInfo> journalEntryIdentifiableInfoRowMapper,
								 RowMapper<CategorizedMonetaryAmount> summarizedJournalEntryRowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.journalEntryQueryBuilder = journalEntryQueryBuilder;
		this.summarizedJournalEntryQueryBuilder = summarizedJournalEntryQueryBuilder;
		this.journalEntryIdentifiableInfoRowMapper = journalEntryIdentifiableInfoRowMapper;
		this.summarizedJournalEntryRowMapper = summarizedJournalEntryRowMapper;
	}
	
	@Override
	public void insertEntry(JournalEntryIdentifiableInfo journalEntry, String createdBy) {
		String sql = " INSERT INTO journal_entry "
				   + " (id, "
				   + " month, "
				   + " day, "
				   + " year, "
				   + " category, "
				   + " value, "
				   + " type,"
				   + " type_modifier,"
				   + " created_by,"
				   + " created_date) "
				   + " VALUES "
				   + " (?, "
				   + "  ?, "
				   + "  ?, "
				   + "  ?, "
				   + "  ?, "
				   + "  ?, "
				   + "  ?,"
				   + "  ?,"
				   + "  ?,"
				   + "  ?) ";
		
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
		String sql = " DELETE FROM journal_entry "
				   + " where id = ? ";
		
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<JournalEntryIdentifiableInfo> queryEntries(
			JournalEntryFilter filter) {
		JdbcQuery query = journalEntryQueryBuilder.buildQuery(filter);
		
		return jdbcTemplate.query(query.getSql(), journalEntryIdentifiableInfoRowMapper, query.getArgs());
	}

	@Override
	public List<CategorizedMonetaryAmount> queryCategorizedAmounts(
			JournalEntryFilter filter) {
		JdbcQuery query = summarizedJournalEntryQueryBuilder.buildQuery(filter);
		
		return jdbcTemplate.query(query.getSql(), summarizedJournalEntryRowMapper, query.getArgs());
	}
	
}