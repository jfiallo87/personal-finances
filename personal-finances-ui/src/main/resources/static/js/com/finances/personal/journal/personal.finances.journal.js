angular.module("personal.finances.journal", ["personal.finances.security", "personal.finances.dictionary", "personal.finances.filter"])
.controller("journalSummary", function($scope, $http, tokenService, filterService) {
	$scope.filter = filterService.newFilter();

	$scope.summary = {};
	
	_loadSummary = function(securityHeaders) {
		$http.get("/personal.finances.service/journal/summary", { params : filterService.prepareFilter($scope.filter.availableFilters[$scope.filter.selectedFilter]),
															 headers : securityHeaders })
			.success(function(data) {
				$scope.summary = data;
			});
	};
	
	$scope.loadSummary = function() {
		tokenService.wrap(_loadSummary);
	};

	$scope.loadSummary();
})
.controller("journal", function($scope, $http, tokenService, filterService) {
	$scope.filter = filterService.newFilter();

	$scope.entries = {};
	
	_loadEntries = function(securityHeaders) {
		$http.get("/personal.finances.service/journal", { params : filterService.prepareFilter($scope.filter.availableFilters[$scope.filter.selectedFilter]),
													 headers : securityHeaders })
			.success(function(data) {
				$scope.entries = data;
			});
	};
	
	$scope.loadEntries = function() {
		tokenService.wrap(_loadEntries);
	};
	
	_deleteEntry = function(securityHeaders) {
		$http.delete("/personal.finances.service/journal/" + $scope.entryIdToDelete, { headers : securityHeaders })
			.success(function(data) {
				$scope.loadEntries();
			});
	};
	
	$scope.deleteEntry = function(entryId) {
		$scope.entryIdToDelete = entryId;
		
		tokenService.wrap(_deleteEntry);
	};

	$scope.loadEntries();
})
.controller("newJournalEntry", function($scope, $http, tokenService, dataDictionaryService) {
	$scope.types = dataDictionaryService.types();
	$scope.months = dataDictionaryService.months();
	$scope.days = dataDictionaryService.days();
	$scope.years = dataDictionaryService.years();
	
	$scope.resetEntry = function() {
		var entry = { date : { month : dataDictionaryService.MONTH.JANUARY,
							   day : 1,
							   year: 2015 },
					  category : { name : null },
					  value : 0.00,
					  type : dataDictionaryService.TYPE.DEBIT };
		
		$scope.entry = entry;
	};
	
	$scope.resetEntry();
	
	_createEntry = function(securityHeaders) {
		$http.post("/personal.finances.service/journal", $scope.entry, { headers : securityHeaders })
			.success(function(data) {
				$scope.resetEntry();
			});
	};
	
	$scope.createEntry = function() {
		tokenService.wrap(_createEntry);
	};
});