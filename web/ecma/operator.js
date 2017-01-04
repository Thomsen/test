var a = '2';
var b = 2;

console.log('s: 2 == i: 2 is ' + (a == b));     // true  -- value equal
console.log('s: 2 === i: 2 is ' + (a === b));   // false -- value and type equal


var c;
var d = null;

console.log('undefined == null is ' + (c == d));     // true  -- value equal
console.log('undefined === null is ' + (c === d));   // false -- value and type equal
