function f1() {
  return 10;
}

console.log(f1 + 20); // function f1(){ return 10; }20 , 函数的隐式转换调用toString

console.log(f1() + 20); // 30

f1.toString = function () {
  return 30;
}

console.log(f1 + 20); // 50


f1.valueOf = function () {
  return 50;
}

console.log(f1 + 20); // 70，valueOf的优先级比toString高


////


var arr = [1, 2, 3, 4].map(function (item, i, arr) {
  console.log(item, i, arr, this);
  return item + 1;
}, { a: 1 });

console.log(arr); // [2, 3, 4, 5]

Array.prototype._map = function (fn, context) {
  var temp = [];
  if (typeof fn == 'function') {
    var k = 0;
    var len = this.length;
    for (; k < len; k++) {
      console.log(context, this[k], k, this);
      temp.push(fn.call(context, this[k], k, this));
    }
  } else {
    console.error("TypeError: " + fn + " is not a function.");
  }

  return temp;
}

var arr2 = [1, 2, 3, 4]._map(function (item) {
  return item + 1;
});

console.log(arr2);

// apply和call非常相似，apply使用的是参数数组，call使用的是参数列表

////

function add() {
  var _args = [].slice.call(arguments);
  console.log('a: ' + _args);  // [1, 2, 3, 4]

  var adder = function () {
    var _adder = function () {
      var __args = [].slice.call(arguments);
      console.log('b: ' + __args);
      console.log('c: ' + [].push.apply(_args, __args));  //  [].push(_args[0], ..., __args[0], ...)，return the new length property of the object
      console.log('d: ' + _adder);   // invoke implicit conversion toString
      return _adder;
    }

    _adder.toString = function () {
      return _args.reduce(function (a, b) {  // arr.reduce(callback, [initialValue])  从左到右应用一个函数
        return a + b;
      });
    }

    return _adder;
  }

  return adder.apply(null, [].slice.call(arguments));  // not strict, null or undefined reference global object
}

console.log(add(1, 2, 3, 4));      // { [Function: _adder] toString: [Function] }
console.log(add(1, 2, 3, 4) + 10); // 20
console.log(add(1, 2, 3)(4));
console.log(add(1, 2, 3)(4) + 10); // 20
console.log(add(1, 2)(3, 4) + 10);
console.log(add(1)(2)(3)(4));

// 将所有参数搜集成数组，并在隐式转换时将所有项加起来

// 柯里化（currying）:又称部分求值，把接收多个参数的函数转化成接收一个单一参数的函数，并返回一个新的函数。
// add(1, 2, 3, 4,) -> add(1)(2)(3)(4)

////


Object.prototype.bind = function (context) {
  var _this = this;
  var args = [].prototype.slice.call(arguments, 1);

  return function () {
    return _this.apply(context, args);
  }
}
