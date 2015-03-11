package com.finances.personal.model;

import java.time.Month;

import com.finances.personal.core.ValueObject;

public class Date extends ValueObject {
	
	public static final String MONTH = "month";
	public static final String DAY = "day";
	public static final String YEAR = "year";

	private Month month;
	private short day;
	private short year;
	
	public final Month getMonth() {
		return month;
	}
	
	public final void setMonth(Month month) {
		lock(MONTH);
		
		this.month = month;
	}

	public final short getDay() {
		return day;
	}

	public final void setDay(short day) {
		lock(DAY);
		
		this.day = day;
	}

	public final short getYear() {
		return year;
	}

	public final void setYear(short year) {
		lock(YEAR);
		
		this.year = year;
	}
	
}