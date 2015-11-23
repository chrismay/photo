'use strict';

// Declare app level module which depends on views, and components
angular.module('photos', [
  'ngRoute',
  'ngFileUpload',
  'photos.competition',
  'photos.history',
  'photos.upload',
  'photos.version'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/current_competition'});
}]);
