var dtAppctrl = angular.module("dtAppctrl", [ "ngRoute" ]);
dtAppctrl
		.config(function($routeProvider, $locationProvider, $httpProvider) {
			$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
			$routeProvider.when("/teams", {
				templateUrl : "partials/dreamteams.html",
				controller : "dreamteams"
			}).when("/teams/:param", {
				templateUrl : "partials/detailteam.html",
				controller : "detailteam"
			}).when("/players", {
				templateUrl : "partials/players.html"
			}).when("/createteam", {
				templateUrl : "partials/createteam.html"
			}).when("/login", {
				templateUrl : "partials/login.html",
				controller : "navigation"
			}).when("/register", {
				templateUrl : "partials/newuser.html",
				controller : "newuser"
			}).otherwise("/", {
				templateUrl : "index.html"
			});
			$locationProvider.html5Mode(true);
		});

dtAppctrl.run(function($rootScope, $location) {
	if ($location.path() === "/register" && !$rootScope.authenticated) {
		return;
	} else if ($rootScope.authenticated) {
		$location.path("/");
	} else {
		$location.path("/login");
	}

});

dtAppctrl.controller('navigation', function($rootScope, $scope, $http,
		$location) {

	var authenticate = function(credentials, callback) {

		var headers = credentials ? {
			authorization : "Basic "
					+ btoa(credentials.username + ":" + credentials.password)
		} : {};

		$http.get('/user', {
			headers : headers
		}).success(function(data) {
			if (data.name) {
				$rootScope.authenticated = true;
				$rootScope.username = data.name;
				$location.path("/");
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback();
		}).error(function() {
			$rootScope.authenticated = false;
			callback && callback();
		});
	}

	authenticate();
	$scope.credentials = {};
	$scope.login = function() {
		authenticate($scope.credentials, function() {
			if ($rootScope.authenticated) {
				$location.path("/");
				$scope.error = false;
			} else {
				$location.path("/login");
				$scope.error = true;
			}
		});
	};
});

dtAppctrl.controller('dtApp', function($scope, $http) {
});

dtAppctrl.controller('restserv', function($scope, $http, $filter) {

	$scope.limit = 5;

	$scope.team = {
		players : []
	};

	$scope.team.name = $scope.name;
	$scope.team.players = [];
	$scope.limit = 5;
	var filteredPlayers = $filter('filter')($scope.players, {
		checked : true
	});
	$scope.disableSelection = function() {
		var filteredPlayers = $filter('filter')($scope.players, {
			checked : true
		});
		return filteredPlayers.length >= $scope.limit;
	}

	$scope.CreateTeam = function(player) {
		$scope.team.name = $scope.name;
		$scope.team.players = [];
		$scope.limit = 4;
		console.log($scope.team.players);
		var filteredPlayers = $filter('filter')($scope.players, {
			checked : true
		});
		angular.forEach(filteredPlayers, function(player) {
			$scope.team.players.push({
				playerId : player.id
			});
		});
		$scope.success = true;
		delete $scope.errors;
		$http.post('/api/createDreamTeam', $scope.team).error(function(data) {
			delete $scope.success;
			$scope.errors = data;
		});
	}

	$http.get('/api/players').success(function(restdata) {
		$scope.players = restdata.players;
		$scope.from = 0
		$scope.to = 50;
		$scope.hasNextPage = true;
		$scope.hasPrevPage = false;
		$scope.count = Math.ceil($scope.players.length / 50);
		$scope.strana = function(page) {
			if (page != "" && page != 1) {
				$scope.from = 50 * (page - 1);
				$scope.to = 50 * page;
				if (page > 1) {
					$scope.hasPrevpage = true;
				} else {
					$scope.hasPrevpage = false;
				}
			} else {
				$scope.from = 0;
				$scope.to = 50;
				$scope.hasNextPage = true;
			}
			if (page >= 78) {
				$scope.from = 50 * 77;
				$scope.to = 50 * 78;
				$scope.hasPrevpage = true;
				$scope.hasNextPage = false;
			}
		}

		$scope.nextPage = function() {
			$scope.hasPrevPage = true;
			$scope.from += 50;
			$scope.to += 50;
			if ($scope.to < 3882) {
				$scope.hasNextPage = true;
			} else {
				$scope.hasNextPage = false;
			}
		}
		$scope.prevPage = function() {
			$scope.hasNextPage = true;
			$scope.to -= 50;
			$scope.from -= 50;
			if ($scope.to == 50) {
				$scope.hasPrevPage = false;
			} else {
				$scope.hasPrevPage = true;
			}
		}

	})

});

dtAppctrl.controller('dreamteams', function($scope, $http) {
	$http.get('/api/dreamteams').success(function(allteams) {
		$scope.teams = allteams;
	})
});

dtAppctrl.controller('detailteam', function($scope, $http, $routeParams) {
	$http.get('/api/dreamteams/' + $routeParams.param).success(function(team) {
		$scope.team = team;
	});
});

dtAppctrl.controller('newuser', function($scope, $http) {
	$scope.createUser = function() {
		$http.post('/api/newuser', {
			username : $scope.username,
			password : $scope.password,
			confirmPassword : $scope.confirmPassword
		}).success(function(userdata) {
			$scope.username = userdata;
			$scope.password = userdata;
			$scope.role = "ROLE_ADMIN";
			$scope.confirmPassword = userdata;
			$scope.success = true;
		}).error(function(data) {
			$scope.errors = data;
		});
	}
})

dtAppctrl.filter('range', function() {
	return function(arr, from, to) {
		return arr ? arr.slice(from, to) : [];
	}
});

dtAppctrl.directive('mainMenu', function() {
	return {
		restrict : "E",
		templateUrl : "/partials/menu.html",
		controller: function($rootScope, $scope, $http, $location) {
			$scope.logout = function() {
			  $http.post('logout', {}).success(function() {
			    $rootScope.authenticated = false;
			    $location.path("/login");
			  }).error(function(data) {
			    $rootScope.authenticated = false;
			  });
			}
		}
	};
})