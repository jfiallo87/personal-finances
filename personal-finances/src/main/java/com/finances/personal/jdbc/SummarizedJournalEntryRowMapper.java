package com.finances.personal.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.finances.personal.model.CategorizedMonetaryAmount;
import com.finances.personal.model.Category;
import com.finances.personal.model.MonetaryAmountType;

@Service
public class SummarizedJournalEntryRowMapper implements
		RowMapper<CategorizedMonetaryAmount> {

	@Override
	public CategorizedMonetaryAmount mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Category category = new Category();
		category.setName(rs.getString("category"));
		
		float total = rs.getFloat("total");
		
		MonetaryAmountType type = MonetaryAmountType.fromAmount(total);
		
		CategorizedMonetaryAmount summarizedJournalEntry = new CategorizedMonetaryAmount();
		summarizedJournalEntry.setCategory(category);
		summarizedJournalEntry.setType(type);
		summarizedJournalEntry.setValue(type.eval(total));
		
		return summarizedJournalEntry;
	}

}
