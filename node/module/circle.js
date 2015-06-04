var PI = Math.PI;

exports.area = function(r) {
  return PI * r * r;
};

exports.circumference = function(r) {
  return 2 * PI * PI;
};


// exports is quote of module.exports
// can modify or add attribute
// cann't assignment value

exports = function(r) {
  return PI * r * r;
};
