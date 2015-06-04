var circle = require('./circle.js');

console.log('The area of a circle of radius 4 is ' + circle.area(4));

//console.log('The area of a circle of radius 2 is ' + circle(2)); // object is not a function

var sequare = require('./square.js');
var mySequare = sequare(4, 5);

console.log('The area of a sequare of width 4 is ' + mySequare.area());

console.log('The area of a sequare of width 2 is ' + sequare(2).area());
// sequare(2) -- is [object Object]
// sequare(2).area() -- is NaN


