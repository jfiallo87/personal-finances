package com.finances.personal.jdbc.model;

import com.finances.personal.core.model.ValueObject;

public class JdbcQuery extends ValueObject {
	
	private static final String SQL = "sql";
	private static final String ARGS = "args";

	private String sql;
	private Object[] args;
	
	public String getSql() {
		return sql;
	}
	
	public void setSql(String sql) {
		lock(SQL);
		
		this.sql = sql;
	}
	
	public Object[] getArgs() {
		return args;
	}
	
	public void setArgs(Object[] args) {
		lock(ARGS);
		
		this.args = args;
	}

}
