

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


// before ecma6
function foo1(msg) {
  console.log('foo1 ' + msg);
}

foo1('a', function(a) {
  if (a.error) {
    throw new Error(a.error);
  }

  foo1('b', function(b) {
    if (b.error) {
      throw new Error(b.error);
    }

    foo1('c', function(c) {
      if (c.error) {
        throw new Error(c.error);
      }

      console.log(a, b, c);
    });
  });
});

// == generator

function* foo2() {
  try {
    var a = yield foo1('a');
    var b = yield foo1('b');
    var c = yield foo1('c');
  } catch (e) {
    console.log(e);
  }

  console.log(a, b, c);
}


// yield*

let delegatedIterator = (function* () {
  yield 'Hello';
  yield 'yield*';
}());

let delegatingIterator = (function* () {
  yield 'Greetings!';
  yield* delegatedIterator;
  yield 'Ok, bye!';
}());

for (let value of delegatingIterator) {
  console.log(value);
}

// array is original yield*
function* genArray() {
  yield ["a", "b", "c"];
  yield* ["d", "e", "f"];
}

var ga = genArray();
console.log(ga.next());
console.log(ga.next());

// yield* nested
function* iterTree(tree) {
  if (Array.isArray(tree)) {
    for (let i=0; i<tree.length; i++) {
      yield* iterTree(tree[i]);
    }
  } else {
    yield tree;
  }
}

const tree = ['a', ['b', 'c'], ['d', 'e', 'f']];
for (let x of iterTree(tree)) {
  console.log(x);
}

// binary tree
function Tree(left, label, right) {
  this.left = left;
  this.label = label;
  this.right = right;
}

function* inorder(t) {
  if (t) {
    yield* inorder(t.left);
    yield t.label;
    yield* inorder(t.right);
  }
}

function make(array) {
  if (array.length == 1) {
    return new Tree(null, array[0], null);
  }
  return new Tree(make(array[0]), array[1], make(array[2]));
}
let bt = make([
  [['a'], 'b', ['c']],
  'd',
  [['e'], 'f', ['g']]
]);

var result = [];
for (let node of inorder(bt)) {
  result.push(node);
}

console.log(result);

// object attribute

let obj1  = {
    * mygen1() {
    }
};

let obj2 = {
  mygen2: function* () {
  }
};


// state machine

// e5
var ticking = true;
var clock1 = function() {
  if (ticking) {
    console.log('Tick!');
  } else {
    console.log('Tock!');
  }
  ticking = !ticking;
};

// e6
var clock2 = function*(_) {
  while(true) {
    yield _;
    console.log('Tick!');
    yield _;
    console.log('Tock!');
  }
};


// coroutine
