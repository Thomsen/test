console.log('a starting');
exports.done = false;
var b = require('./cycle_b.js');
console.log('in a, b.done = %j', b.done);
exports.done = true;
console.log('a done');
