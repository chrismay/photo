'use strict';

angular.module('photos.version', [
  'photos.version.interpolate-filter',
  'photos.version.version-directive'
])

.value('version', '0.1');
