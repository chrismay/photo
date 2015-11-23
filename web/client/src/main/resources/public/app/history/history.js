'use strict';

angular.module('photos.history', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/history', {
            templateUrl: 'history/history.html',
            controller: 'historyCtrl'
        });
    }])
    .controller('historyCtrl', ['$scope','$http', function ($scope,$http) {
        var ctrl = this;

        $scope.new_topic = "New Topic";

        $scope.create_competition = function(){
            $http.post("/competition?topic=" + $scope.new_topic).then(ctrl.refresh,ctrl.refresh);
        };


        this.refresh = function(){
            $http.get("/competitions").success(function(data){
                $scope.competitions = data;
            });
        };
        this.refresh();
    }]);

