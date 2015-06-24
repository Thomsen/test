
function foo() {
  var args = Array.from(arguments); // TypeError: Array.from is not a function

  args.forEach(function(a) {
    console.log(a);
  });
}

foo('a', 'b', 'c');
