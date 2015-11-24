(function () {
    "use strict";

    angular.module('photos')
        .controller('CompetitionController', CompetitionController);

    CompetitionController.$inject = ['competitionService'];

    function CompetitionController(competitionService) {

        var vm = this;
        vm.current = {};
        vm.vote_for_photo = "";
        vm.voter_name = "";

        vm.vote = vote;

        activate();

        function activate() {
            competitionService.getCurrent().then(function (data) {
                vm.current = data;
            });
        }



        function vote() {
            function showVoteSuccess(){
                alert("voted");
            }
            function showVoteFailure(){
                alert("voting failed");
            }

            competitionService.vote(vm.vote_for_photo, vm.voter_name)
                .then(showVoteSuccess, showVoteFailure);
        }
    }
})();