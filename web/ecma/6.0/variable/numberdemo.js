
console.log('0b111110111 === 503 : ' + (0b111110111 === 503));

console.log('0o767 === 503 : ' + (0o767 === 503));

console.log('15 is finite : ' + Number.isFinite(15));
console.log('NaN is finite : ' + Number.isFinite(NaN));
console.log('Infinity is finite : ' + Number.isFinite(Infinity));
console.log('-Infinity is finite : ' + Number.isFinite(-Infinity));
console.log('\'foo\' is finite : ' + Number.isFinite('foo'));
console.log('\'15\' is finite : ' + Number.isFinite('15'));
console.log('true is finite : ' + Number.isFinite(true));

console.log('NaN is NaN : ' + Number.isNaN(NaN));
console.log('15 is NaN : ' + Number.isNaN(15));
console.log('\'15\' is NaN : ' + Number.isNaN('15'));
console.log('true is NaN : ' + Number.isNaN(true));


(function (global) {
  var global_isFinite = global.isFinite;

  Object.defineProperty(Number, 'isFinite', {
    value: function isFinite(value) {
      return typeof value === 'number' && global_isFinite(value);
    },
    configurable: true,
    enumerable: false,
    writable: true
  });
})(this);

(function (global) {
  var global_isNaN = global.isNaN;

  Object.defineProperty(Number, 'isNaN', {
    value: function isNaN(value) {
      return typeof value === 'number' && global_isNaN(value);
    },
    configurable: true,
    enumerable: false,
    writable: true
  });
})(this);

console.log('15 is finite : ' + isFinite(15));
console.log('\'15\' is finite : ' + isFinite('15'));
console.log('NaN is finite : ' + isFinite(NaN));

console.log('NaN is NaN : ' + isNaN(NaN));
//console.log('NaN is NaN : ' + Number.isNaN(NaN)); // TypeError: global_isNaN is not a function
console.log('\'NaN\' is NaN : ' + isNaN('NaN'));
console.log('\'NaN\' is NaN : ' + Number.isNaN('NaN')); // 

//

// es5
console.log(parseInt("12.34"));
console.log(parseFloat('123.456#'));

// es6
console.log(Number.parseInt("12.34"));
console.log(Number.parseFloat('123.456#'));


console.log('25 is integer : ' + Number.isInteger(25));
console.log('25.0 is integer : ' + Number.isInteger(25.0));
console.log('25.1 is integer : ' + Number.isInteger(25.1));
console.log('\'25\' is integer : ' + Number.isInteger('25'));
console.log('true is integer : ' + Number.isInteger(true));

/*
// es5 
(function (global) {

  var floor = Math.floor, isFinite = global.isFinite;

  Object.defineProperty(Number, 'isInteger', {
    value: function isInteger(value) {
      return typeof value === 'number' && isFinite(value) &&
        value > -9007199254740992 && value < 9007199254740992 &&
        floor(value) === value;
    },
    configurable: true,
    enumerable: false,
    writable: true
    
  });
  
})(this);
*/

var inside = Number.MAX_SAFE_INTEGER;
console.log('max safe integer ' + inside);

var outside = inside + 1;

console.log(inside + ' is integer : ' + Number.isInteger(inside));
console.log(inside + ' is safe integer : ' + Number.isSafeInteger(inside));

console.log(outside + ' is integer : ' + Number.isInteger(outside));
console.log(outside + ' is safe integer : ' + Number.isSafeInteger(outside));

//

console.log('math trunc 4.1 is ' + Math.trunc(4.1));
console.log('math sign 5 is ' + Math.sign(5));

