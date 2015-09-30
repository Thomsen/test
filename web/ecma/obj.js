// direct character

var objdc = {x: "abc", "y": 12.3};

// new operation

var objno = new Object();
objno.x = "abc";
objno["y"] = 12.3;

// object create

var objoc = Object.create(Object.prototype);
objoc["x"] = "abc";
objoc["y"] = 12.3;


console.log(JSON.stringify(Object.getOwnPropertyDescriptor(objdc)));
console.log(JSON.stringify(Object.getOwnPropertyDescriptor(objno)));
console.log(JSON.stringify(Object.getOwnPropertyDescriptor(objoc)));


var rectangle = {
  x: 2.0,
  y: 4.0,
  a: 0.0,
  get h() { // hypotenuse
    return Math.sqrt(this.x * this.x + this.y * this.y);
  },
  set h(newvalue) {
    var oldvalue = Math.sqrt(this.x * this.x + this.y * this.y);
    var ratio = newvalue / oldvalue;
    this.x *= ratio;
    this.y *= ratio;
  },
  get theta() {
    return Math.atan2(this.y, this.x);
  },
  print: function() {
    console.log("x: " + this.x + ", y: " + this.y);
  }
};

console.log(rectangle);
console.log(Object.getOwnPropertyDescriptor(rectangle, 'x'));
console.log(Object.getOwnPropertyDescriptor(rectangle, 'h'));
console.log(Object.getOwnPropertyDescriptor(rectangle, 'theta'));
console.log(Object.getOwnPropertyDescriptor(rectangle, 'x'));

console.log(delete rectangle.a);

console.log(Object.getOwnPropertyDescriptor({x: 1}, 'x'));

var defprop = Object.defineProperties({}, {
  x: {value: 1, writable: true, enumerable: true, configurable: true}
});

console.log(Object.getOwnPropertyDescriptor(defprop, 'x'));
