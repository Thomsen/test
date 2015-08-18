
{
  let a = 'i am declared inside an anonymous block by let';
  var b = 'i am declared inside an anonymous block by var';

  console.log("a = " + a);
}

// console.log("a = " + a); // a is not defined

console.log("b = " + b);

// node 0.10 -- SyntaxError: Unexpected identifier
// node 0.12 also (node --v8-options | grep harmony
// io.js success
// Block-scoped declarations (let, const, function, class) not yet supported outside strict mode
// -- iojs --use_strict letdemo.js

// for use let
var a = [];
var b = [];

for (let i=0; i<10; i++) {
  a[i] = function() {
    console.log('for let ' + i);
  };
}

for (var j=0; j<10; j++) {
  b[j] = function() {
    console.log('for var ' + j);
  };
}

a[6](); // 6
b[6](); // 10


// no varialbe promotion
if (1) {
  console.log(typeof d); // undefined
  var d;
  //console.log(typeof c); // ReferenceError: c is not defined
  let c;
}


// temporal dead zone

var tmp = 123;
var pmt = 321;
if (2) {
  pmt = 'cba';
  //tmp = 'abc'; // ReferenceError: tmp is not defined
  var pmt;
  let tmp;
  console.log('if pmt = ' + pmt);
  console.log('if tmp = ' + tmp);
}
console.log('pmt = ' + pmt);
console.log('tmp = ' + tmp);

/*
function bar(x=y, y=2) { // SyntaxError: Unexpected token =
  return [x, y];
}
bar();
 */


let foo = 'outer';
/*
let f = {
};
function bar(func = f) {
  let foo = 'inner';
  console.log(func()); //
}
*/

// no repeat
//let foo = 'repeat'; // SyntaxError: Identifier 'foo' has already been declared

(function () {
  var tm = 'iife';
  console.log(tm);
}());
// ==
{
  let tm = 'block';
  console.log(tm);
}


function f() {
  console.log('i am outside!');
}
(function () {
  if (false) {
    function f() {
      console.log('i am inside!'); // strict mode no support (if wihile)
    }
  }
  f(); // es6 -- i am outside! es5 -- i am inside!
}()); // )();

// var -- function-scoped, let -- block-scoped
