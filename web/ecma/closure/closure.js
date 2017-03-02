for (var i = 0; i < 5; i++) {
  console.log('a: ' + i);
}


for (var i = 0; i < 5; i++) {
  setTimeout(function () {
    console.log('b: ' + i);  // 5, 5, 5, 5, 5
  }, 1000 * i);
}


for (var i = 0; i < 5; i++) {
  (function () {
    setTimeout(function () {
      console.log('c: ' + i); // 5, 5, 5, 5, 5
    }, i * 1000);
  })(i);
}


for (var i = 0; i < 5; i++) {
  (function (i) {
    setTimeout(function () {
      console.log('d ' + i);  // 0, 1, 2, 3, 4
    }, i * 1000);
  })(i);
}


for (let i = 0; i < 5; i++) {
  setTimeout(function () {
    console.log('dd ' + i);  // 0, 1, 2, 3, 4
  })
}


// for (var i = 0; i < 5; i++) {
//   setTimeout(function (i) {
//     console.log('e: ' + i);  //
//   } (i), i * 1000);
// }
// e: 0
// timers.js:327
//     throw new TypeError('"callback" argument must be a function');


setTimeout(function () {
  console.log('f: ' + 0);
}, 0);

new Promise(function executor(resolve) {
  console.log('f: ' + 1);
  for (var i = 0; i < 10000; i++) {
    i == 9999 && resolve();
  }
  console.log('f: ' + 2);
}).then(function () {
  console.log('f: ' + 3);
});
console.log('f: ' + 4);
//  output: 1 2 4 3 0


var f1 = null;

function foo() {
  var a = 2;
  function innerFoo() {
    console.log('g: ' + a);
  }
  f1 = innerFoo;
}

function bar() {
  f1();
}

console.log('foo:');
foo();  // 执行完后，生命周期结束，所占内存被垃圾回收器释放。但由于f1得到innerFoo引用，无法回收
console.log('bar:');
a = 3;
bar();  // g: 2，可以访问被保留下来的变量对象。foo称为闭包


// 闭包，能够访问独立变量的函数，这些函数可以“记忆”它被创建时的环境。
