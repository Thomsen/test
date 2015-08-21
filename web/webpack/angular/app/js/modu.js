var one = require('./modu_one');

var mo = angular.module('modu', []);

mo.controller('moductrl', function($scope) {
  $scope.test = function() {
    console.log("modu test");
    one.test();
  };
});
