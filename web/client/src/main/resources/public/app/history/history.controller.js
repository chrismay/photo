(function () {
    "use strict";

    angular.module('photos')
        .controller('HistoryController', HistoryController);

    HistoryController.$inject = ['competitionService'];

    function HistoryController(competitionService) {
        var vm = this;

        vm.new_topic = "New Topic";
        vm.create_competition  = create_competition;
        vm.competitions = [];
        vm.activate = activate;

        activate();

        function create_competition() {
            competitionService.create(vm.new_topic).then(vm.activate);
        }

        function activate() {
            competitionService.getAll().then(function (response) {
                vm.competitions = response.data;
            });
        }
    }
})();
