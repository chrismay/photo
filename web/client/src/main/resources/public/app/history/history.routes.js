(function () {
    'use strict';

    angular.module('photos')
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/history', {
                templateUrl: 'history/history.html',
                controller: 'HistoryController',
                controllerAs: 'vm'
            });
        }]);
})();