
var app = angular.module('myWeatherApp', []);
app.controller('myCtrl', function($scope, $http, $interval, $window, $filter) {
	console.log(sessionStorage.getItem('username'))

	if (!sessionStorage.getItem('userID')) {
		$window.location.href = 'index.html';
	}

	$scope.locationMessage = '';
	$scope.weatherApiKey = '500c619e5ccd58fabda20e71b84bddda';
	$scope.latitude = '';
	$scope.longitude = '';

	$scope.weatherHistory = [];

	$scope.demoApi = function() {
		$http.get('/demo')
			.then(function(response) {
				$scope.hello = response.data;

			})
			.catch(function(error) {
				console.error('Error fetching data:', error);
			});
	}

	$scope.getLocation = function() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				$scope.$apply(function() {
					$scope.locationMessage = 'Latitude: ' + position.coords.latitude + ', Longitude: ' + position.coords.longitude;

					$scope.latitude = position.coords.latitude;
					$scope.longitude = position.coords.longitude;

					$http.get('https://api.openweathermap.org/data/2.5/weather?lat=' + position.coords.latitude + '&lon=' + position.coords.longitude + '&appid=' + $scope.weatherApiKey + '&units=metric')
						.then(function(response) {
							$scope.data = response.data;

							var postData = {
								userId: sessionStorage.getItem('userID'),
								locationName: response.data.name,
								latitude: $scope.latitude,
								longitude: $scope.longitude,
							};
							// Make the POST request using $http.post
							$http.post("/createUserLocation", postData)
								.then(function(response) {
									$scope.scheduleMinutes = response.data.scheduleMinutes
									$scope.userLocationId = response.data.userLocationId
									console.log('Success:', response.data);
									$scope.startTimer();
								})
								.catch(function(error) {

									console.error('Error:', error);
								});
						})
						.catch(function(error) {
							console.error('Error fetching data:', error);
						});
				});
			}, function(error) {
				switch (error.code) {
					case error.PERMISSION_DENIED:
						$scope.$apply(function() {
							$scope.locationMessage = 'User denied the request for Geolocation.';
						});
						break;
					case error.POSITION_UNAVAILABLE:
						$scope.$apply(function() {
							$scope.locationMessage = 'Location information is unavailable.';
						});
						break;
					case error.TIMEOUT:
						$scope.$apply(function() {
							$scope.locationMessage = 'The request to get user location timed out.';
						});
						break;
					case error.UNKNOWN_ERROR:
						$scope.$apply(function() {
							$scope.locationMessage = 'An unknown error occurred.';
						});
						break;
				}
			});
		} else {
			$scope.locationMessage = 'Geolocation is not supported by this browser.';
		}
	};

	$scope.getLocation();

	$scope.getChart = function() {

		var ctx = document.getElementById('weatherChart').getContext('2d');
		var labels = $scope.weatherHistory.map(function(item) {
			return $filter('date')(item.createdOn, 'dd/MM/yyyy HH:mm');
		});
		var data = $scope.weatherHistory.map(function(item) {
			return item.avgTemparature;
		});

		$scope.weatherChart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: labels,
				datasets: [{
					label: 'Temperature (°C)',
					data: data,
					borderColor: 'rgb(75, 192, 192)',
					tension: 0.1
				}]
			},
			options: {
				scales: {
					y: {
						beginAtZero: false
					}
				},
				plugins: {
					tooltip: {
						enabled: true,
						position: 'nearest',
						mode: 'index',
						callbacks: {
							label: function(context) {
								return context.dataset.label + ': ' + context.parsed.y + '°C';
							}
						}
					}
				}
			}
		});
	}


	$scope.time = {
		minutes: 0,
		seconds: 0
	};

	var stop;

	$scope.startTimer = function() {
		// Don't start a new timer if one is already running
		if (angular.isDefined(stop)) return;

		stop = $interval(function() {
			$scope.time.seconds++;

			if ($scope.time.seconds === 60) {
				$scope.time.seconds = 0;
				$scope.time.minutes++;
			}

			// API call every 1 minute
			if ($scope.time.seconds === 0 && $scope.time.minutes % 1 === 0) {
				$scope.callApi();
			}
		}, 1000);
	};

	$scope.stopTimer = function() {
		if (angular.isDefined(stop)) {
			$interval.cancel(stop);
			stop = undefined;
		}
	};

	$scope.resetTimer = function() {
		$scope.time = {
			minutes: 0,
			seconds: 0
		};
	};

	$scope.callApi = function() {
		var postData = {
			userLocationId: sessionStorage.getItem('userID'),
		};

		$http.post("/fetchUserLocationHistoryResponse", postData)
			.then(function(response) {
				$scope.weatherHistory = [];
				$scope.weatherHistory = response.data;
				console.log('Success:', response.data);

				var canvas = document.getElementById('weatherChart');
				if (canvas.getContext) {
					var ctx = canvas.getContext('2d');
					// Clear canvas
					ctx.clearRect(0, 0, canvas.width, canvas.height);
					if ($scope.weatherChart) {
						$scope.weatherChart.destroy(); // Destroy existing chart instance
					}
					$scope.getChart();
				} else {
					console.error('Canvas not supported!');
				}
			})
			.catch(function(error) {
				console.error('Error:', error);
			});
	};

	$scope.$on('$destroy', function() {
		// Make sure that the interval is destroyed too
		$scope.stopTimer();
	});

	$scope.updateTime = function() {
		$scope.stopTimer();
		/*alert($scope.newMinutes)*/

		var postData = {
			userId: sessionStorage.getItem('userID'),
			scheduleTime: $scope.newMinutes,
			ifOnlyScheduleTimeUpdate: true
		};

		// Make the POST request using $http.post
		$http.post("/createUserLocation", postData)
			.then(function(response) {
				$scope.userLocationId = response.data.userLocationId
				$scope.scheduleMinutes = response.data.scheduleMinutes;
				$scope.startTimer();
				console.log('Success:', response.data.scheduleMinutes);
				/*alert("success")*/
			})
			.catch(function(error) {
				console.error('Error:', error);
			});

	};
	$scope.logout = function() {
		$window.sessionStorage.clear();
		$window.location.href = 'index.html';
	}
});

app.filter('fahrenheitToCelsius', function() {
	return function(input) {
		return ((input - 32) * 5 / 9).toFixed(2); // Formula to convert Fahrenheit to Celsius
	};
});


