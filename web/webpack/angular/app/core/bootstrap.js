'use strict';

require('angular');

var appModule = require('../index');

angular.element(document).ready(function() {
  angular.bootstrap(document, [appModule.name], {});
});
