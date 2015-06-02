process.stdin.on('data', function(buf) {
  console.log(buf);
});

process.stdin.on('end', function() {
  console.log('__END__');
});


// (echo beep; sleep 1; echo boop) | node classic_read_one.js
