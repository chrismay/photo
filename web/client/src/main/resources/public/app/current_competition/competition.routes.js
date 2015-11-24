(function () {
    'use strict';

    angular.module('photos')
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/current_competition', {
                templateUrl: 'current_competition/competition.html',
                controller: 'CompetitionController',
                controllerAs: 'vm'
            });
        }]);
})();

