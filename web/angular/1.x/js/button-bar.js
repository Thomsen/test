var bb = angular.module('button-bar', []);

bb.controller('BtnBarController', ['$scope', '$window', function($scope, $window) {
  console.log('bb ctrl scope id = ', $scope.$id );
  $scope.primaryLabel = 'Prim';
  $scope.primaryClick = function() {
    $window.alert('Prim clicked');
  };

}]);

bb.directive('primary', function() {
  return {
    restrict: 'C',
    link: function(scope, element, attrs) {
      element.addClass('btn btn-primary');
    }
  };
});


bb.directive('buttonBar', function() {
  return {
    restrict: 'EA',
    template: '<div class="span4 well clearfix"><div class="pull-right" ng-transclude>></div></div>',
    replace: true,
    transclude: true
  };
});
