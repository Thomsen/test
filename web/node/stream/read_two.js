var Readable = require('stream').Readable;

var rs = new Readable();

var c = 97 - 1;
rs._read = function () {
  if (c > 'z'.charCodeAt(0)) {
    rs.push(null);
  }
  setTimeout(function () {
    rs.push(String.fromCharCode(++c));
  }, 100);
};

rs.pipe(process.stdout);

process.on('exit', function() {
  console.error('\n_read() called ' + (c - 97) + ' times');
});

process.stdout.on('error', process.exit);

// node read_two.js | head -c5
