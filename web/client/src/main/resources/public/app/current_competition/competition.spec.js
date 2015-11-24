(function () {
    "use strict";

    describe('photos.current_competition module', function () {

        beforeEach(module('photos'));

        describe('current_competition controller', function () {

            it('should define the controller', inject(function ($controller, $httpBackend) {

                $httpBackend.when('/competition/current').respond({});

                var controller = $controller('CompetitionController');

                expect(controller).toBeDefined();
            }));

        });
    });
})();