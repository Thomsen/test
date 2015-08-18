
const PI = 3.1415;
console.log("PI = " + PI);

/* // es5
 Object.defineProperty(typeof global === "object" ? global : window, "PI", {
   value: 3.1415,
   enumerable: true,
   writable: false,
   configurable: false
 })
 */

PI = 3; // default error -- strict mode TypeError: Assignment to constant variable.
console.log("PI = " + PI);

//const PI = 3.1; // TypeError: Identifier 'PI' has already been declared
console.log("PI = " + PI);


if (true) {
  console.log("MAX = " + MAX); // undefined
  const MAX = 1024;
}
console.log("MAX = " + MAX); // 1024

const foo = {};
foo.prop = 123;

console.log('foo.prop = ' + foo.prop);

foo = {};
console.log('foo.prop = ' + foo.prop);

const fooz = Object.freeze({});
fooz.prop = 321;
console.log('fooz.prop = ' + fooz.prop);

// iojs --v8-options | grep "in progress"
