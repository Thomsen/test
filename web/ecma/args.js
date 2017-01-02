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

a(2, 3, function () {
  console.log("a callback");
});

var b = function (d, callback) {
  var len = arguments.length;
  console.log("fun b args length " + len);
  if (callback instanceof Function) {
    callback();
  }
};

b();
b(3, 2, 1);
b(2, function () {
  console.log('b callback');
});

function argsToArray() {
  var args = Array.prototype.slice.call(arguments);
  console.log('args: ');
  console.log(args);

  var argsa = Array.prototype.slice.call(arguments);
  console.log('argsa: ');
  argsa = argsa.toString();
  console.log(argsa);
  argsa = argsa.replace('[', '(');
  argsa = argsa.replace(']', ')');
  console.log(argsa);

  var argsd = Array.prototype.slice.call(arguments);
  console.log('argsd: ');
  console.log(argsd);
  argsd.join(',');
  console.log(argsd);
  var argse = '(' + argsd + ')';
  console.log(argse);

  var argsc = Array.prototype.slice.call(arguments) | [];
  console.log('argsc: ');
  console.log(argsc);  // 0 or 1

  console.log('-------------\n\n');
}

argsToArray();
argsToArray(1);
argsToArray('1', '2', '3');
