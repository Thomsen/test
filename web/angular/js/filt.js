var filt = angular.module('filt', []);

filt.controller('filtctrl', function($scope) {
  $scope.num = 3232;
  $scope.date = new Date();

  $scope.time = $scope.date.getTime();
});

filt.filter("fullname", function() {
  var filterfun = function (person, sep) {
    sep = sep || " ";
    person = person || {};
    person.first = person.first || "";
    person.last = person.last || "";
    return person.first + sep + person.last;
  };
  return filterfun;
});
