'use strict';

describe('photo uploading', function() {

  beforeEach(module('photos'));
  var upload = function(){};
  var photoService = function(){};

  describe('upload controller', function(){

    it('should define a controller', inject(function($controller) {
      //spec body
      var controller = $controller('UploadController',{photoService:photoService, Upload:upload,$timeout:1,$routeParams:{} });
      expect(controller).toBeDefined();
    }));

  });
});