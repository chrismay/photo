'use strict';

angular.module('photos.upload', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/upload/:competitionId', {
            templateUrl: 'upload/upload.html',
            controller: 'uploadCtrl'
        });
    }])

    .controller('uploadCtrl', ['$scope', 'Upload', '$timeout', '$routeParams', function ($scope, Upload, $timeout, $routeParams) {

        $scope.competitionId = $routeParams.competitionId;

        $scope.uploadPic = function (file) {

            file.upload = Upload.upload({
                url: '/competition/' +$routeParams.competitionId + '/photos',
                data: {file: file, caption: $scope.caption},
            });

            file.upload.then(function (response) {
                $timeout(function () {
                    file.result = response.data;
                });
            }, function (response) {
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            }, function (evt) {
                // Math.min is to fix IE which reports 200% sometimes
                file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
            });
        }
    }]);