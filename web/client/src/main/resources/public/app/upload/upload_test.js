'use strict';

describe('photos.upload module', function() {

  beforeEach(module('photos.upload'));
  var scope;
  beforeEach(function () {
    scope = {};
  });
  var upload = function(){};

  describe('upload controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var view2Ctrl = $controller('uploadCtrl',{$scope:scope, Upload:upload,$timeout:1,$routeParams:{} });
      expect(view2Ctrl).toBeDefined();
    }));

  });
});