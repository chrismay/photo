(function () {
    "use strict";

    angular.module('photos')
        .controller('UploadController', UploadController);

    UploadController.$inject = ['photoService','$timeout','$routeParams'];

    function UploadController( photoService, $timeout, $routeParams) {

        var vm = this;
        vm.caption = "";
        vm.competitionId = $routeParams.competitionId;
        vm.uploadPic = uploadPic;


        function uploadPic(file) {

            file.upload = photoService.upload(vm.competitionId, file, vm.caption);

            file.upload.then(
                function (response) {
                    $timeout(function () {
                        file.result = response.data;
                    });
                }, function (response) {
                    if (response.status > 0)
                        vm.errorMsg = response.status + ': ' + response.data;
                }, function (evt) {
                    // Math.min is to fix IE which reports 200% sometimes
                    file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
                });
        }
    }
})();
