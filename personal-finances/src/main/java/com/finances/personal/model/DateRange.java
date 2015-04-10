package com.finances.personal.model;

import com.finances.personal.core.model.Lockable;

public class DateRange extends Lockable {
	
	public static final String FROM_DATE = "fromDate";
	public static final String TO_DATE = "toDate";
	
	private Date fromDate;
	private Date toDate;
	
	public final Date getFromDate() {
		return fromDate;
	}
	
	public final void setFromDate(Date fromDate) {
		lock(FROM_DATE);
		
		this.fromDate = fromDate;
	}

	public final Date getToDate() {
		return toDate;
	}

	public final void setToDate(Date toDate) {
		lock(TO_DATE);
		
		this.toDate = toDate;
	}

}