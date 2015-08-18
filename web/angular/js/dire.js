var dire = angular.module('dire', []);

dire.directive('hello', function() {
  return {
    restrict: 'E',
    template: '<div>Hi here</div>',
    replace: true
  };
});

dire.directive('hello2', function() {
  return {
    restrict: 'E',
    template: '<div>Hi here2 <span ng-transclude></span></div>',
    transclude: true
  };
});

dire.controller('helloctrl', function($scope) {
  $scope.things = [1, 2, 3, 4, 5, 6];
});

dire.directive('hello3', function() {
  return {
    restrict: 'A',
    template: '<div>Hi here3</div>',
    replace: true
  };
});

dire.directive('hello4', function() {
  return {
    restrict: 'C',
    template: '<div>Hi here4</div>',
    replace: true
  };
});

dire.directive('hello5', function() {
  return {
    restrict: 'M',
    template: '<div>Hi here5</div>',
    replace: true
  };
});
