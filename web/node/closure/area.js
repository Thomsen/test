
function area_square(x) {
  this.x = x;
  return x * x;
}

var area_rectange = function(x, y) {
  var y = y;
  return x * y;
} // right is anonymous function

var area_square2 = new Function("x", 'return x*x;');  // not recomment

console.log('2 square = ' + area_square(2));
console.log('2 3 rectange = ' + area_rectange(2, 3));
console.log('2 squre2 = ' + area_square(2));

//console.log("g is " + g);  // g is not defined
//console.log("h is " + h);  // h is not defined
console.log("x is "  + x);
//console.log("y is " + y);  // y is not defined


var rect;
(function(x, y) {
  g = x;
  var h = y;
  rect =  x * y;
})(2, 3);

console.log('2 3 rect = ' + rect);

console.log("g is " + g);
//console.log("h is " + h); // h is not defined
console.log("x is "  + x);
//console.log("y is " + y); // y is not defined


var circular;
var PI = 3.14;
(function(r){
  a = r;
  circular = PI * r * r;
}(2));

console.log("2 circular = " + circular);

console.log("a is " + a);


function checkClosure() {
  var str = 'outter main'; // 函数执行完毕后立即释放
  var tm = 5000; // 函数执行后不能立即释放，在setTimeout中被引用。被执行完后才能释放
  setTimeout(function() {
    console.log("timeeout " + (tm / 1000) + " s" );
  }, tm);
}

checkClosure();
