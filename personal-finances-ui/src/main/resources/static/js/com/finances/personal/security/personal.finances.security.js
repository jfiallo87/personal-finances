angular.module("personal.finances.security", [])
.factory("tokenService", function($http) {
	return {
		wrap : function(callback) {
			$http.get('token').success(function(token) {
				callback({ 'X-Auth-Token' : token.token });
			});
		}
	};
})
.controller("authentication", function($rootScope, $scope, $http, $location) {
	var authenticate = function(callback) {

		$http.get("user").success(function(data) {
			if (data.name) {
				$rootScope.authenticated = true;
			} else {
				$rootScope.authenticated = false;
			}
			callback();
		}).error(function() {
			$rootScope.authenticated = false;
			callback();
		});

	}

	authenticate(function() {
		if ($rootScope.authenticated) {
			$location.path("/");
		} else {
			$location.path("/login");
		}
	});

	$scope.credentials = {};

	$scope.login = function() {
		$http.post("login", $.param($scope.credentials), { headers : { "content-type" : "application/x-www-form-urlencoded" } })
			.success(function(data) {
				 authenticate(function() {
					 if ($rootScope.authenticated) {
						 $location.path("/");
						 $scope.error = false;
					 } else {
						 $location.path("/login");
					 	 $scope.error = true;
					 }
				 });
			})
			.error(function(data) {
				$location.path("/login");
				$scope.error = true;
				$rootScope.authenticated = false;
			})
	};

	$scope.logout = function() {
		$http.post('logout', {})
			.success(function() {
				$rootScope.authenticated = false;
				$location.path("/login");
			})
			.error(function(data) {
				console.log("Logout failed")
				$rootScope.authenticated = false;
				$location.path("/login");
			});
	}
});