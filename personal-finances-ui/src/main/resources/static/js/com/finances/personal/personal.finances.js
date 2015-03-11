angular.module("personal.finances", ["personal.finances.dictionary",
                                     "personal.finances.filter",
                                     "personal.finances.security",
                                     "personal.finances.journal",
                                     "ngTagsInput",
                                     "ngRoute",
                                     "ngAnimate"])
.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "template/home.html",
		controller : "journalSummary"
	}).when("/entries", {
		templateUrl : "template/entries.html",
		controller : "journal"
	}).when("/entries/new", {
		templateUrl : "template/new.entry.html",
		controller : "newJournalEntry"
	}).when("/entries/summary", {
		templateUrl : "template/home.html",
		controller : "journalSummary"
	}).when("/login", {
		templateUrl : "template/login.html",
		controller : "authentication"
	}).otherwise("/");
});