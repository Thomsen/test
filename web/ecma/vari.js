var a = "apple";  // primitive
var b = a;        // value copy
a = "orange";

console.log("a = " + a);  // orange
console.log("b = " + b);  // apple

var c = {name: "apple"}; // reference
var d = c;               // address copy

c.name = "orange";

console.log("c name = " + c.name); // orange
console.log("d name = " + d.name); // orange


var e = {name: "apple"}; // reference
var f = e;               // address copy

e = {name: "orange"};    // address restrict

console.log("e name = " + e.name); // orange
console.log("f name = " + f.name); // apple

var g = {name: "apple"};  // reference
var h = g.name;           // value copy

g.name = "orange";

console.log("g name = " + g.name); // orange
console.log("h = " + h);           // apple


// primitive values(Undefined, Null, Boolean, Number, String) and reference values(Memory Object)

var i = ["apple"];
var j = ["apple"];

console.log("i == j is " + (i == j));   // false
console.log("i === j is " + (i === j)); // false

// 两个引用类型比较，'=='和'==='没有区别

// 两个基本类型比较，
// 不同类型比较，'=='会转化成同一类型后的值，然后比较；'==='类型不同，则结果不等；
// 同类型比较，直接值比较

// 基本类型和引用类型
// '=='会将引用类型转换为基本类型，进行值比较；'==='因为类型不同，结果不等。
