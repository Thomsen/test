

"use strict";

function* helloWorldGenerator() {
  yield 'hello';
  yield 'world';
  return 'ending';
}

var hw = helloWorldGenerator();

console.log('hw[Symbol.iterator]() === hw : ' + hw[Symbol.iterator]() === hw);

console.log(hw.next());

for (let h of hw ) {
  console.log(h);
}


function* ftim() {
  console.log('execute ftim');
}

var ft = ftim();

setTimeout(function() {
  ft.next();
}, 2000);


function* foo(x) {
  var y = 2 * (yield (x + 1));
  var z = yield(y / 3);
  return (x + y + z);
}

var fo = foo(5);

var a = fo.next().value;
var b = fo.next(a).value;
var c = fo.next(b).value;

console.log("a = " + a);
console.log("b = " + b);
console.log("c = " + c);


// throw

var g = function* () {
  while (true) {
    try {
      yield;
    } catch (e) {
      if (e != 'a') {
        throw e;
      }
      console.log('inner catch', e);
    }
  }
};

var gi = g();
gi.next();

try {
  gi.throw('a');
  gi.throw('b');
} catch (e) {
  console.log('outer catch', e);
}

try {
  throw new Error('a');
} catch(e) {
  console.log('outer error catch', e);
}
