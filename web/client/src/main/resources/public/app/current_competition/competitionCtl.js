"use strict";

angular.module('photos')
    .controller('competitionCtl', function($http, $scope) {

        var ctrl = this;
        $scope.new_topic = "New Topic";

        $scope.create_competition = function(){
            $http.post("/competition?topic=" + $scope.new_topic).then(ctrl.refresh,ctrl.refresh);
        };

        this.refresh = function(){
            $http.get("/competition/current").success(function(data){
                $scope.competition = data;
            });
        };
        this.refresh();

    });