var fsp = angular.module('fsp', []);

fsp.controller('FspController', function($scope, Fsp, FspFactory, FspService) {
  $scope.division = function() {
    $scope.data.result = $scope.data.number1 * $scope.data.number2;
  };
  $scope.division_provider = function() {
    $scope.data.result = Fsp.division($scope.data.number1, $scope.data.number2);
  };
  $scope.division_factory = function() {
    $scope.data.result = FspFactory.plus($scope.data.number1, $scope.data.number2);
  };
  $scope.division_service = function() {
    $scope.data.result = FspService.reduction($scope.data.number1, $scope.data.number2);
  };
});

fsp.provider('Fsp', function() {
  this.divisor = -1;
  this.$get = function() {
    var divisor_nozero = this.divisor;
    return {
      division: function(total, divisor) {
        if (divisor && divisor != 0) {
          divisor_nozero = divisor;
        }
        return total / divisor_nozero;
      }
    };
  };

  this.setDivisor = function(divisor) {
    this.divisor = divisor;
  };
});

fsp.config(function(FspProvider) {
  FspProvider.setDivisor(1);
});

fsp.factory('FspFactory', function() {
  return {
    plus: function(augend, addend) {
      //return augend + addend; // 1 + 2 := 12
      return parseFloat(augend) + parseFloat(addend);
    }
  };
});

fsp.service('FspService', function() {
  this.reduction = function(minuend, meiosis) {
    return minuend - meiosis;
  };
});
