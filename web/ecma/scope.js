var scope1 = "global scope";
function test1() {
  console.log(scope1);  // undefined，作用域基于函数

  var scope1 = "local scope";
  console.log(scope1);  // local scope
}

console.log(scope1);  // global scope

test1();
console.log(this);  // {}
console.log(__filename);
console.log(__dirname);
console.log(global);

// note: javascript没有块级作用域，有的只是函数作用域
var scope2 = "global scope2";
if (true) {
  var scope2 = "local scope2";
  console.log(scope2);  // local scope2
}

console.log(scope2);    // local scope2

// 函数作用域，变量在声明它们的函数体以及这个函数体嵌套的任意函数体内都是有定义的。
var scope3 = "global scope3";
function test3() {
  var scope3;
  console.log(scope3);  // undefined。scope1与scope3都有声明，并覆盖了全局scope，但是没有赋值

  scope3 = "local scope3";
  console.log(scope3);  // local scope3
}

console.log(scope3);  // global scope3

test3();

// 函数中块域作用在函数域上

var test4 = function (flag) {
  if (flag) {
    var scope4 = "if scope4";  // 这里的var很有意思：
    // 如果没有var，先执行test4()会报scope4 is not defined。先执行test4(true)会输出(2, if scope4)，再执行test4()会输出(undefined, if scope4)
    // 如果有var，先执行test4()会输出(undefined, undefined)。先执行test4(true)会输出(2, if scope4)，在执行test4()会输出(undefined, undefined)
    // note: js中没有用var声明的变量都是全局变量
    // js的执行分为词法分析阶段和运行阶段，词法分析阶段先声明，运行阶段进行赋值（没赋值undefined）
    // 如果没有var，scope4会直接进入运行阶段，先执行test()则报is not defined，而i4没有报
    for (var i4 = 0; i4 < 2; i4++) {
      ;
    }
  }
  console.log(i4);
  console.log(scope4);
};

test4();     // undefined, undefined
test4(true); // 2, if scope4
test4();  // undefined, undefined

//console.log(scope4);  // ReferenceError: scope4 is not defined。 没有声明，编译报错

// 作用域链表

var scope5 = "global scope5";
function test5() {
  var scope5 = "local scope5";
  function test5_i() {
    var scope5 = "local inside scope5";  // 如果没有var，则执行test5()输出(local inside scope5, local inside scope5, local inside scope5)
    console.log(scope5);
  }
  function test5_ii() {
    console.log(scope5);
  }
  test5_i();  // test5_i() -> test5() -> window
  test5_ii();  // test5_ii() -> test5() -> window

  console.log(scope5);
}

test5();  // (local inside scope5, local scope5, local scope5)
console.log(scope5);  // (global scope5)


var scope6 = { name: 'husband', age: 28, height: 175, wife: { name: 'wife', age: 25 } };

console.log(scope6.wife.name);

with (scope6.wife) {
  console.log(name);  // (wife) with语句临时将语句中的对象添加到作用域头部
}


//

function test7(scope7) {
  console.log(scope7);
}

function test8(scope7) {
  console.log(scope7);
}

function test9(scope7) {
  console.log(scope7);
}

test7("test scope7");  // test scope7
test8();               // undefined
test8("test scope8");  // test scope8
