package com.finances.personal.jdbc;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import com.finances.personal.model.Category;
import com.finances.personal.model.JournalEntryFilter;
import com.finances.personal.model.MonetaryAmount;
import com.finances.personal.model.MonetaryAmountType;

public abstract class AbstractJournalEntryQueryBuilder {
	
	public JdbcQuery buildQuery(JournalEntryFilter filter) {
		List<Object> args = new ArrayList<Object>();
		
		String sql = getBaseSql();
		
		sql = appendCategoriesFilter(sql, args, filter);
		sql = appendDateRangeFilter(sql, args, filter);
		sql = appendAmountRangeFilter(sql, args, filter);
		
		sql += getGrouping();
		
		JdbcQuery query = new JdbcQuery();
		query.setSql(sql);
		query.setArgs(args.toArray());
		
		return query;
	}

	protected abstract String getBaseSql();
	
	protected abstract String getGrouping();

	private String appendCategoriesFilter(String sql, List<Object> args,
			JournalEntryFilter filter) {
		if (!CollectionUtils.isEmpty(filter.getCategories())) {
			String[] placeholders = ArrayUtils.EMPTY_STRING_ARRAY;
			
			for (Category category : filter.getCategories()) {
				placeholders = ArrayUtils.add(placeholders, "?");
				
				args.add(category.getName());
			}
			
			sql += " and category in (" + StringUtils.arrayToCommaDelimitedString(placeholders) + ") ";
		}
		
		return sql;
	}

	private String appendDateRangeFilter(String sql, List<Object> args,
			JournalEntryFilter filter) {
		if (filter.getDateRange() != null) {
			com.finances.personal.model.Date fromDate = filter.getDateRange().getFromDate();
			com.finances.personal.model.Date toDate = filter.getDateRange().getToDate();
			
			String journalEntryDate = " str_to_date(concat(cast(year as char(4)), '/', cast(month as char(2)), '/', cast(day as char(2))), '%Y/%m/%d') ";
			String parameterizedJournalEntryDate = " str_to_date(concat(cast(? as char(4)), '/', cast(? as char(2)), '/', cast(? as char(2))), '%Y/%m/%d') ";
				
			if (fromDate != null) {
				Month month = ObjectUtils.defaultIfNull(fromDate.getMonth(), Month.JANUARY);
				short day = fromDate.getDay();
				short year = fromDate.getYear();
				
				sql += " and " + journalEntryDate + " >= " + parameterizedJournalEntryDate;

				args.add(year);
				args.add(month.getValue());
				args.add(day);
			}
			
			if (toDate != null) {
				Month month = ObjectUtils.defaultIfNull(toDate.getMonth(), Month.DECEMBER);
				short day = (toDate.getDay() > 0) ? toDate.getDay() : NumberUtils.convertNumberToTargetClass(month.maxLength(), Short.class);
				short year = (toDate.getYear() > 0) ? toDate.getYear() : 9999;
				
				sql += " and " + journalEntryDate + "<=" + parameterizedJournalEntryDate;

				args.add(year);
				args.add(month.getValue());
				args.add(day);
			}
		}
		
		return sql;
	}

	private String appendAmountRangeFilter(String sql, List<Object> args,
			JournalEntryFilter filter) {
		if (filter.getAmountRange() != null) {
			MonetaryAmount fromAmount = filter.getAmountRange().getFromAmount();
			MonetaryAmount toAmount = filter.getAmountRange().getToAmount();
			
			if (fromAmount != null) {
				float value = (fromAmount.getType() != null) ? fromAmount.getType().eval(fromAmount.getValue()) : MonetaryAmountType.DEBIT.eval(Float.MAX_VALUE);
				
				sql += " and round(value * type_modifier, 2) >= ? ";
				
				args.add(value);
			}
			
			if (toAmount != null) {
				float value = (toAmount.getType() != null) ? toAmount.getType().eval(toAmount.getValue()) : Float.MAX_VALUE;
				
				sql += " and round(value * type_modifier, 2) <= ? ";
				
				args.add(value);
			}
		}
		
		return sql;
	}

}
