package com.finances.personal.model;

public class JournalEntryInfo extends CategorizedMonetaryAmount {
	
	public static final String DATE = "date";
	
	private Date date;

	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		lock(DATE);
		
		this.date = date;
	}

}