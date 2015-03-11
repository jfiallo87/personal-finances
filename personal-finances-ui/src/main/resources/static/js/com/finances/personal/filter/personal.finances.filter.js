angular.module("personal.finances.filter", ["personal.finances.dictionary"])
.factory("filterService", function(dataDictionaryService) {
	return {
		newFilter : function() {
			var startYear = 2000;
			var currentYear = dataDictionaryService.currentYear();
			var startMonth = dataDictionaryService.MONTH.JANUARY;
			var endMonth = dataDictionaryService.MONTH.DECEMBER;
			var defaultValue = 9999.99;

			var types = dataDictionaryService.types();
			var months = dataDictionaryService.months();
			var days = dataDictionaryService.days();
			var years = dataDictionaryService.years();

			var filters = ["All", "All Credits", "All Debits", "Custom"];
			
			var selectedFilter = filters[0];

			var availableFilters = {};

			availableFilters["All"] = {
					categoryNames : [],
					categories : []};

			availableFilters["All Credits"] = {
				fromType : dataDictionaryService.TYPE.CREDIT,
				fromValue : 0,
				categoryNames : [],
				categories : []
			};

			availableFilters["All Debits"] = {
				fromType : dataDictionaryService.TYPE.DEBIT,
				fromValue : defaultValue,
				toType : dataDictionaryService.TYPE.CREDIT,
				toValue : 0,
				categoryNames : [],
				categories : []
			};

			availableFilters["Custom"] = {
				fromType : dataDictionaryService.TYPE.DEBIT,
				fromValue : defaultValue,
				fromMonth : startMonth,
				fromDay : 1,
				fromYear : startYear,
				toType : dataDictionaryService.TYPE.CREDIT,
				toValue : defaultValue,
				toMonth : endMonth,
				toDay : 31,
				toYear : currentYear,
				categoryNames : [],
				categories : []
			};

			return {
				types : types,
				months : months,
				days : days,
				years : years,
				filters : filters,
				selectedFilter : selectedFilter,
				availableFilters : availableFilters
			};
		},
		prepareFilter : function(filter) {
			var preparedFilter = {
				fromType : filter.fromType,
				fromValue : filter.fromValue,
				fromMonth : filter.fromMonth,
				fromDay : filter.fromDay,
				fromYear : filter.fromYear,
				toType : filter.toType,
				toValue : filter.toValue,
				toMonth : filter.toMonth,
				toDay : filter.toDay,
				toYear : filter.toYear,
				categoryNames : []
			};
			
			angular.forEach(filter.categories, function(category, i) {
				preparedFilter.categoryNames.push(category.text);
			}, preparedFilter);
			
			return preparedFilter;
		}
	};
})
.directive('journalFilter', function() {
	return {
		restrict : "E",
		scope : {
			model : "="
		},
		templateUrl : "directive/journal-filter.html"
	};
});