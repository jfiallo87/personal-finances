package com.finances.personal.jdbc.model;

public class JournalEntryTable extends Table {
	
	private JournalEntryTable() {}
	
	public static JournalEntryTable describe() {
		return new JournalEntryTable();
	}
	
	@Override
	public String tableName() {
		return "journal_entry";
	}
	
	public String id() {
		return "id";
	}
	
	public String month() {
		return "month";
	}
	
	public String day() {
		return "day";
	}
	
	public String year() {
		return "year";
	}
	
	public String category() {
		return "category";
	}
	
	public String value() {
		return "value";
	}
	
	public String type() {
		return "type";
	}
	
	public String typeModifier() {
		return "type_modifier";
	}
	
	public String createdBy() {
		return "created_by";
	}
	
	public String createdDate() {
		return "created_date";
	}
	
}