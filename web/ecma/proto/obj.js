var obj1 = {
  a: 1,
  b: 2
};
// obj1 ---> Object.prototype ---> null
// Object.prototype.hasOwnProperty

var arr1 = ['th', 'om', '!'];
// arr1 ---> Array.prototype ---> Object.prototype ---> null
// Array.prototype.indexOf/forEach

function fun1() {
  return 1;
}
// fun1 ---> Function.prototype ---> Object.prototype ---> null
// Function.prototype.call/bind

var f1 = new fun1();
console.log("fun1.call " + fun1.call());
// console.log("f1.call " + f1.call());  // is not a function

var obj2 = {
  b: 3,
  c: 4
};

var obj3 = Object.create(obj2);

console.log(obj3.b);
console.log(obj3.c);

// obj3 ---> obj2 ---> Object.prototype ---> null

obj3.d = 5;


var obj4 = Object.create(obj3);  // Object.create create object
obj4.e = 6;
console.log(obj4.b);
console.log(obj4.c);
console.log(obj4.d);
console.log(obj4.e);

// obj4 ---> obj3 ---> obj2 ---> Object.prototype ---> null

var obj5 = Object.create(null);

console.log(obj4.hasOwnProperty);
console.log(obj5.hasOwnProperty);

// obj5 ---> null


function fun2() {
  this.abc = [];
  this.ddd = [];
}

fun2.prototype = {
  addAbc: function (v) {
    this.abc.push(v);
  }
};

var f2 = new fun2();  // constructor function create object

// f2 ---> fun2.prototype --> null

console.log(f2.hasOwnProperty);
console.log("fun2.call " + fun2.call());  // undefined
// console.log(f2.call());  // is not a function
