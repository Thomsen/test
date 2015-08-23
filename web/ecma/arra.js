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
