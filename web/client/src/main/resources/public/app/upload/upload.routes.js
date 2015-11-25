(function () {
    'use strict';

    angular.module('photos')
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/upload/:competitionId', {
                templateUrl: 'upload/upload.html',
                controller: 'UploadController',
                controllerAs: 'vm'
            });
        }]);
})();

