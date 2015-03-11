package com.finances.personal.model;

public class JournalEntryIdentifiableInfo extends JournalEntryInfo {
	
	public static final String ID = "id";
	
	private String id;

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		lock(ID);
		
		this.id = id;
	}

}