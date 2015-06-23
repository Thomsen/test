
'use strict';

var m = new Map();

var mo = {p: "hello map"};

m.set(mo, 'value content');

console.log("mo content " + m.get(mo));

console.log('m has mo = ' + m.has(mo));

console.log('m delete mo = ' + m.delete(mo));

console.log('m has mo = ' + m.has(mo));


var mp2 = new Map([
  ['name', 'thom'],
  ['title', 'developer']
]);

console.log('mp2 size = ' + mp2.size);

console.log('mp2 has name = ' + mp2.has('name') + ' and name is ' + mp2.get('name'));

mp2.set(['a'], 567);
console.log('mp 2 has object referenct = ' + mp2.get(['a']));

mp2.set(NaN, 123);
console.log('mp2 get NaN = ' + mp2.get(NaN));

mp2.set(-0, 456);
console.log('mp2 get 0 = ' + mp2.get(+0));

// iterator

for (let key of mp2.keys()) {
  console.log(key);
}

for (let value of mp2.values()) {
  console.log(value);
}

for (let item of mp2.entries()) {
  console.log(item[0], item[1]);
}

/*
for (let [key, value] of mp2) {
  console.log(key, value);
}
 */


var wm_counter = new WeakMap();
var wm_action = new WeakMap();

class Countdown {
  constructor(counter, action) {
    wm_counter.set(this, counter);
    wm_action.set(this, action);
  }

  dec() {
    let counter = wm_counter.get(this);
    if (counter < 1) {
      return ;
    }
    counter --;
    wm_counter.set(this, counter);
    if (counter === 0) {
      wm_action.get(this)();
    }
  }
  
}


let c = new Countdown(2, function() {
  console.log("done");
});

c.dec();
c.dec();

