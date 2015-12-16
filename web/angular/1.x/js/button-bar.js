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

bb.directive('secondary', function() {
  return {
    restrict: 'C',
    link: function(scope, element, attrs) {
      element.addClass('btn');
    }
  };
});

bb.directive('buttonBar', function() {
  return {
    restrict: 'EA',
    template: '<div class="span4 well clearfix"><div class="primary-block pull-right"></div><div class="secondary-block"></div><div class="transcluded" ng-transclude></div></div>',
    replace: true,
    transclude: true,
    link: function(scope, element, attrs) {
      var ec = element.children();
      var econ = element.contents();
      var ef = element.find();  // need jqlite, angular not use dom

      // var primaryBlock = element.children('.primary-block.pull-right');
      // var secondaryBlock = element.children('div.secondary-block');
      // var transcludedBlock = element.children('div.transcluded');

      // var primaryBlock = element[0].getElementsByClassName('primary-block')[0];
      // var secondaryBlock = element[0].getElementsByClassName('secondary-block')[0];
      // var transcludedBlock = element[0].getElementsByClassName('transcluded')[0];
      // var transcludedButtons = transcludedBlock.getElementsByTagName('button');

      var primaryBlock = angular.element(element[0].querySelector('.primary-block'));
      var secondaryBlock = angular.element(element[0].querySelector('.secondary-block'));
      var transcludedBlock = angular.element(element[0].querySelector('.transcluded'));
      var transcludedButtons = transcludedBlock.children();

      angular.forEach(transcludedButtons, function(elem) {
        if (angular.element(elem).hasClass('primary')) {
          primaryBlock.append(elem);
        } else if (angular.element(elem).hasClass('secondary')) {
          secondaryBlock.append(elem);
        }
      });
      transcludedBlock.remove();
    }
  };
});
