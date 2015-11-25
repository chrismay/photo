(function () {
    "use strict";

    angular.module('photos').factory('photoService', photoService);

    function photoService(Upload) {
        var service = {
            upload: upload

        };
        return service;

        function upload(competitionId, file, caption){
            return Upload.upload({
                url: '/competition/' +competitionId + '/photos',
                data: {file: file, caption: caption}
            });
        }

    }
})();