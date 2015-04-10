package com.finances.personal.model;

import com.finances.personal.core.model.Identifiable;

public class JournalEntryIdentifiableInfo extends JournalEntryInfo implements Identifiable {
	
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