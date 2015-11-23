"use strict";

angular.module('photos')
    .controller('competitionCtl', function ($http, $scope) {


        $scope.vote = function () {
            $http.post("/photo/" + $scope.vote_for_photo + "/votes?user=" + $scope.voter_name);
        };


        $http.get("/competition/current").success(function (data) {
            $scope.competition = data;
        });

    });