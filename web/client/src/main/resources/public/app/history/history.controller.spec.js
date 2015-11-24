(function () {
    "use strict";

    describe('photos history', function () {

        beforeEach(module('photos'));

        describe('history controller', function () {
            it('should define the controller', inject(function ($controller, $httpBackend) {

                $httpBackend.when('GET','/competitions').respond({});

                var controller = $controller('HistoryController');

                expect(controller).toBeDefined();
            }));

            it('should load competitions on activation', inject(function ($controller, $httpBackend) {

                var data = ["one","two"];
                $httpBackend.when('GET','/competitions').respond(data);

                var controller = $controller('HistoryController');

                expect(controller).toBeDefined();
                $httpBackend.flush();
                expect(controller.competitions).toEqual(data);
            }));

        });
    });
})();
