function a(c) {
  var len = arguments.length;
  console.log("fun a args length " + len);
  console.log("c is " + c);

  if (3 == len) {
    var fun = arguments[2];
    fun();
  }
}

a();
a(1);
a(1, 2);

a(2, 3, function() {
  console.log("a callback");
});

var b = function(d, callback) {
  var len = arguments.length;
  console.log("fun b args length " + len);
  if (callback instanceof Function) {
    callback();
  }
};

b();
b(3, 2, 1);
b(2, function() {
  console.log('b callback');
});
