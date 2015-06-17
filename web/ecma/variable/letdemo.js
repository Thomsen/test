
{
  let a = 'i am declared inside an anonymous block by let';
  var b = 'i am declared inside an anonymous block by var';

  console.log("a = " + a);
}

// console.log("a = " + a); // a is not defined

console.log("b = " + b);

// node 0.10 -- SyntaxError: Unexpected identifier
// node 0.12 also (node --v8-options | grep harmony
// io.js success
// Block-scoped declarations (let, const, function, class) not yet supported outside strict mode
// -- iojs --use_strict letdemo.js

