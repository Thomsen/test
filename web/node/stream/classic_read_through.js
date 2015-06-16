var through = require('through');

process.stdin.pipe(through(write, end));

function write(buf) {
  console.log(buf);
}

function end() {
  console.log('__END__');
}


// (echo beep; sleep 1; echo boop) | node classic_read_through.js

// sudo yum install nodejs-through

