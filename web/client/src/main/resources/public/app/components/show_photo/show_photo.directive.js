(function() {
  'use strict';
  angular.module('photos')
    .directive('showPhoto', photoDirective);

  function photoDirective(){
    return {
      restrict: 'E',
      scope: {photo: '=photo'},
      templateUrl: 'components/show_photo/show_photo.directive.html'
    };

  }
})();
