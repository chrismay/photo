'use strict';

angular.module('photos.competition', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/current_competition', {
    templateUrl: 'current_competition/competition.html',
    controller: 'competitionCtl'
  });
}]);

