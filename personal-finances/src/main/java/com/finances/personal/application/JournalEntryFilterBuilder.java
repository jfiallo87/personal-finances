package com.finances.personal.application;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finances.personal.model.Category;
import com.finances.personal.model.Date;
import com.finances.personal.model.DateRange;
import com.finances.personal.model.JournalEntryFilter;
import com.finances.personal.model.MonetaryAmount;
import com.finances.personal.model.MonetaryAmountRange;
import com.finances.personal.model.MonetaryAmountType;

@Service
public class JournalEntryFilterBuilder {
	
	public JournalEntryFilter buildFilter(MonetaryAmountType fromType, Float fromValue,
										  MonetaryAmountType toType, Float toValue,
										  Month fromMonth, Short fromDay, Short fromYear,
										  Month toMonth, Short toDay, Short toYear,
										  List<String> categoryNames) {
		JournalEntryFilter filter = new JournalEntryFilter();
		filter.setAmountRange(buildAmountRange(fromType, fromValue, toType, toValue));
		filter.setDateRange(buildDateRange(fromMonth, fromDay, fromYear,
				 						   toMonth, toDay, toYear));
		filter.setCategories(buildCategories(categoryNames));
		
		return filter;
	}

	private List<Category> buildCategories(List<String> categoryNames) {
		List<Category> categories = new ArrayList<Category>();
		
		if (categoryNames != null) {
			for (String categoryName : categoryNames) {
				Category category = new Category();
				category.setName(categoryName);
				
				categories.add(category);
			}
		}
		
		return categories;
	}

	private DateRange buildDateRange(Month fromMonth, Short fromDay, Short fromYear,
									 Month toMonth, Short toDay, Short toYear) {
		Date fromDate = buildDate(fromMonth, fromDay, fromYear);
		Date toDate = buildDate(toMonth, toDay, toYear);
		
		DateRange dateRange = null;
		
		if (fromDate != null && toDate != null) {
			dateRange = new DateRange();
			dateRange.setFromDate(fromDate);
			dateRange.setToDate(toDate);
		}
		
		return dateRange;
	}

	private Date buildDate(Month month, Short day, Short year) {
		Date fromDate = null;
		
		if (month != null && day != null && year != null) {
			fromDate = new Date();
			fromDate.setMonth(month);
			fromDate.setDay(day);
			fromDate.setYear(year);
		}
		
		return fromDate;
	}

	private MonetaryAmountRange buildAmountRange(MonetaryAmountType fromType,
			Float fromValue, MonetaryAmountType toType, Float toValue) {
		MonetaryAmount fromAmount = buildAmount(fromType, fromValue);
		MonetaryAmount toAmount = buildAmount(toType, toValue);
		
		MonetaryAmountRange amountRange = null;
		
		if (fromAmount != null && toAmount != null) {
			amountRange = new MonetaryAmountRange();
			amountRange.setFromAmount(fromAmount);
			amountRange.setToAmount(toAmount);
		}
		
		return amountRange;
	}

	private MonetaryAmount buildAmount(MonetaryAmountType type, Float value) {
		MonetaryAmount amount = null;
		
		if (type != null && value != null) {
			amount = new MonetaryAmount();
			amount.setType(type);
			amount.setValue(value);
		}
		
		return amount;
	}

}