(function () {
    "use strict";

    angular.module('photos').factory('competitionService', competitionService);

    function competitionService($http){


        var service = {
            getCurrent : getCurrent,
            vote : vote,
            create: create,
            getAll: getAll
        };

        return service;

        function getCurrent(){
            return $http.get("/competition/current").then(function (response) {
                return response.data;
            });
        }

        function vote(photoId, user){
            return $http.post("/photo/" + photoId + "/votes?user=" + user);
        }

        function create(topic){
            return $http.post("/competition?topic=" + topic);
        }

        function getAll(){
            return $http.get("/competitions");
        }
    }


})();