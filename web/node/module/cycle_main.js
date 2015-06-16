console.log('main starting');
var a = require('./cycle_a.js');
var b = require('./cycle_b.js');
console.log('in main a.done = %j, b.done=%j', a.done, b.done);
