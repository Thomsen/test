
"use strict";

var s = new Set([1, 2, 3, 4, 5, 5, 5, 6]);

console.log('s length: ' + s.size);

s.add({});
console.log('s length: ' + s.size);

s.add({});
console.log('s length: ' + s.size);

console.log('s has 5 : ' + s.has(5));

console.log('s has {} : ' + s.has({}));

s.delete(5);
console.log('s has 5 : ' + s.has(5));

for (let item of s.keys()) {
  console.log('set keys item ' + item);
}

for (let item of s.values()) {
  console.log('set values item ' + item);
}

for (let item of s.entries()) {
  console.log('set entries item ' + item);
}

console.log('Set.prototype[Symbol.iterator] === Set.prototype.values => ' + (Set.prototype[Symbol.iterator] === Set.prototype.values));  // no bracket is false

for (let x of s) {
  console.log(x);
}

/*
let arr = [...set];
for (let y of arr) {
  console.log(y);
}
 */

var ws = new WeakSet();
//ws.add(1); // TypeError: Invalid value used in weak set

ws.add([1]);

ws.add({});

console.log('ws length = ' + ws.size);  // size undefined

/*
ws.forEach(function(item) {
  console.log('weakset has ' + item);
}); // TypeError: ws.forEach is not a function
 */


