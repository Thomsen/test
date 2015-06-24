
var s = '字';

console.log(s + ' length = ' + s.length);

console.log(s + ' char at 0 = ' + s.charAt(0));
console.log(s + ' char code at 0 = ' + s.charCodeAt(0));

s = '吉';

console.log(s + ' length = ' + s.length);

console.log(s + ' char at 0 = ' + s.charAt(0));
console.log(s + ' char code at 0 = ' + s.charCodeAt(0));

s = new String('字a');

console.log(s + ' length = ' + s.length);

console.log(s + ' char at 0 = ' + s.charAt(0));
console.log(s + ' char code at 0 = ' + s.charCodeAt(0));

s = '𠮷'; // 0x20BB7 -- utf-16 (0xD842 0xDFB7)

console.log(s + ' length = ' + s.length);

console.log(s + ' char at 0 = ' + s.charAt(0));
console.log(s + ' char code at 0 = ' + s.charCodeAt(0));
console.log(s + ' char code at 1 = ' + s.charCodeAt(1));

// es6
console.log(s + ' code point at 0 = ' + s.codePointAt(0));
console.log(s + ' code point at 1 = ' + s.codePointAt(1));

function is32Bit(c) {
  return c.codePointAt(0) > 0xFFFF;
}

//

console.log('from char code 0x20BB7 is ' + String.fromCharCode(0x20BB7));

console.log('from code point 0x20BB7 is ' + String.fromCodePoint(0x20BB7));

console.log('es5 String.prototype.charAt ' + s.charAt(0));

//console.log('es7 String.prototype.at ' + s.at(0));

console.log('\/u0061 is ' + "\u0061");

// regex

console.log(/^.$/.test(s));

//console.log(/^.$/u.test(s));

console.log(/\u{61}/.test('a'));

//console.log(/\u{61}/u.test('a'));

/*
function codePointLength(text) {
  var result = text.match(/[\s\S]/gu);
  return result ? result.length : 0;
}

console.log(s + ' length is ' + codePointLength(s));
*/

console.log('normalize : ' + ('\u01D1'.normalize() === '\u004F\u030C'.normalize()));

//

var ss = 'hello world';

console.log('startsWith hello is ' + ss.startsWith('hello'));

console.log('endsWith world is ' + ss.endsWith('world'));

console.log('inlcudes o is ' + ss.includes('o'));

console.log('repeat twice is ' + ss.repeat(2));

//console.log('es7 regex ' + RegExp.escape("(*.*)"));

console.log(`string text line 1
string text line 2`);

console.log('string text line 1'
 + 'string text line 2');

var name = 'thom', time = 'today';

console.log(`hello ${name}, how are you ${time}?`);


var total = 30;

console.log(`The total is ${total} (${total*1.05} with tax)`);

var msg = passthru`The total is ${total} (${total*1.05} with tax)`;

function passthru(literals) {
  var result = "";
  var i = 0;

  while (i < literals.length) {
    result += literals[i++];
    if (i < arguments.length) {
      result += arguments[i];
    }
  }
  return result;
}

console.log(msg);

//

console.log(`Hi\n${2+3}!`);
console.log(String.raw`Hi\n${2+3}!`);

console.log(`Hi\u000A!`);
console.log(String.raw`Hi\u000A!`);

console.log({raw: 'test'}, 0, 1, 2);
console.log(String.raw({raw: 'test'}, 0, 1, 2));
console.log({raw: ['t', 'e', 's', 't']}, 0, 1, 2);
console.log(String.raw({raw: ['t', 'e', 's', 't']}, 0, 1, 2));

