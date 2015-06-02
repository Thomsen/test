process.stdin.on('readable', function() {
  var buf = process.stdin.read(3); // read(3)
  console.dir(buf);
  process.stdin.read(0);
});

// (echo abc; sleep 1; echo def; sleep 1; echo gh) | node read_consume.js

// read();
//   <Buffer 61 62 63 0a>
//   <Buffer 64 65 66 0a>
//   <Buffer 67 68 0a>

// read(3);
//   <Buffer 61 62 63>
//   <Buffer 0a 64 65>
//   <Buffer 66 0a 67>

// process.stdin.read(0);
//   <Buffer 61 62 63>
//   <Buffer 0a 64 65>
//   <Buffer 66 0a 67>
//   <Buffer 68 0a>

