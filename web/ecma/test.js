for (var i = 0; i < 10; i++) {
  console.log("for i = " + i);
}
console.log("outer i = " + i);


var a = { n: 1 };
var b = a;
a.x = a = { n: 2 };
console.log("a.x = " + a.x);  // undefined
console.log("b.x = " + b.x);  // object

console.log("a.n = " + a.n);  // 2
console.log("b.n = " + b.n);  // 1
console.log("b.x.n = " + b.x.n);  // 2
