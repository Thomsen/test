var obj = ['b', 3, 0];
for (var o in obj) {
  console.log('o = ' + o + " obj[" + o + "] = " + obj[o]);
  obj[obj[o]] = o; // obj[b] = 0 obj[3] = 1 obj[0] = 2, obj[1] = 3 obj[2] = 0
}
console.log('obj[\'b\'] = ' + obj['b']);

obj[{'a': 0}] = {'b': 1};
obj[{'a': 1}] = {'b': 2};
console.log('obj[{\'a\': 0}] = ' + obj[{'a': 0}]);
console.log(obj[{'a': 0}]);
console.log(obj[{'a': 1}]);
console.log(obj[{'b': 2}]);

obj['name'] = 'aa';
console.log(obj.name);

obj['2bit'] = 'bb';
//console.log(obj.2bit); // 2bit not valid indentify, object too. Unexpected token ILLEGAL

var cc = 'ccc';
obj['ccc'] = 'cccc';

console.log('obj.cc = ' + obj.cc);
console.log('obj[\'cc\'] = ' + obj[cc]);

for (var oo in obj) {
  console.log('oo = ' + oo + " obj[" + oo + "] = " + obj[oo]);
}

console.log("obj json: " + JSON.stringify(obj));

// ================================================ //

var window = {};
var navigator = {};
var camera = {};

window.navigator = navigator;
navigator.camera = camera;

camera.getPicture = function() {
  console.log("take picture");
};

var symbolPath = "navigator";
var parts = symbolPath.split('.');

var cur = window;
console.log("cur w " + JSON.stringify(cur));

for (var i=0, part; part = parts[i]; i++) {
  cur = cur[part] = cur[part] || {};
  console.log("cur " + i + " " + JSON.stringify(cur));
}

var lastName = "camera";
var target = cur[lastName];

console.log("cur t " + target);

target.getPicture();
navigator.camera.getPicture();
camera.getPicture();
