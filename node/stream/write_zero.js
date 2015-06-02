var Writable = require('stream').Writable;
var ws = Writable();

ws._write = function(chunk, enc, next) {
  console.dir(chunk);
  next();
};

process.stdin.pipe(ws);

// (echo beep; sleep 1; echo boop) | node write_zere.js
