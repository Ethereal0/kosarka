var dtAppctrl = angular.module("dtAppctrl", [ "ngRoute" ]);

dtAppctrl.config(function($routeProvider, $locationProvider) {
    $routeProvider
    .when("/teams", {
        templateUrl : "partials/dreamteams.html",
        	controller: "dreamteams"
    })
    .when("/teams/:param", {
        templateUrl : "partials/detailteam.html",
        	controller: "detailteam"
    })
    .when("/players", {
        templateUrl : "partials/players.html"
    })
    .when("/createteam", {
        templateUrl : "partials/createteam.html"
    })
    .when("/blue", {
        templateUrl : "partials/blue.htm"
    })
    .otherwise("/", {
    	templateUrl : "index.html"
    });
    // use the HTML5 History API
   $locationProvider.html5Mode(true);
});


dtAppctrl.controller('dtApp', function($scope, $http) {
	$http.get('/api/res').success(function(data) {
		$scope.message = data;
	})
	$scope.setUser = function() {
		$http.post('/api/setuser', $scope.username).success(function(username) {
			$scope.message = username;
		})
	}
});

dtAppctrl.controller('restserv', function($scope, $http, $filter) {

	
	$scope.team = {
		players : []
	};
	$scope.CreateTeam = function() {
		$scope.team.name = $scope.name;
		$scope.team.userId = 6;
		$scope.team.players = [];
		console.log($scope.team.players);
		var filteredPlayers = $filter('filter')($scope.players, {
			checked : true
		});
		if ((filteredPlayers.length != 0) && (filteredPlayers.length <= 5)) {
			angular.forEach(filteredPlayers, function(player) {
				$scope.team.players.push({
					playerId : player.id
				});
			});
		} else if (filteredPlayers.length > 5) {
			console.log('Selektovano je previse igraca');
		} else {
			console.log('greska')
		}

		$http.post('/api/createDreamTeam', $scope.team);
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

dtAppctrl.filter('range', function() {
	return function(arr, from, to) {
		return arr ? arr.slice(from, to) : [];
	}
});