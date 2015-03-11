angular.module("personal.finances.dictionary", [])
.factory("dataDictionaryService", function() {
	var _MONTH = {
		JANUARY : "JANUARY",
		FEBRUARY : "FEBRUARY",
		MARCH : "MARCH",
		APRIL : "APRIL",
		MAY : "MAY",
		JUNE : "JUNE",
		JULY : "JULY",
		AUGUST : "AUGUST",
		SEPTEMBER : "SEPTEMBER",
		OCTOBER : "OCTOBER",
		NOVEMBER : "NOVEMBER",
		DECEMBER : "DECEMBER"
	};
	
	var _TYPE = {
		CREDIT : "CREDIT",
		DEBIT : "DEBIT"
	};
	
	return {
		MONTH : _MONTH,
		TYPE : _TYPE,
		currentYear : function() {
			return new Date().getFullYear();
		},
		types : function() {
			return [ _TYPE.CREDIT, _TYPE.DEBIT ];
		},
		months : function() {
			return [ _MONTH.JANUARY, _MONTH.FEBRUARY, _MONTH.MARCH,
			         _MONTH.APRIL, _MONTH.MAY, _MONTH.JUNE, _MONTH.JULY, _MONTH.AUGUST,
			         _MONTH.SEPTEMBER, _MONTH.OCTOBER, _MONTH.NOVEMBER,
			         _MONTH.DECEMBER ];
		},
		days : function() {
			var days = [];
			
			for (day = 1; day <= 31; day++) {
				days.push(day);
			}
			
			return days;
		},
		years : function() {
			var years = [];
			
			var endYear = new Date().getFullYear();
			
			for (year = 2000; year <= endYear; year++) {
				years.push(year);
			}
			
			return years;
		}
	}
});