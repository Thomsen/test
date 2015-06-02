var concat = require('concat-stream');

process.stdin.pipe(concat(function (body) {
  console.log(JSON.parse(body));
}));

// echo '("beep":"boop"}' | node classic_read_concat.js

// sudo yum install nodejs-concat-stream
