function Person() {
  this.name = 'thom';
}

var person = new Person();

Person.prototype.say = function() {
  console.log("hi,", this.name);
};

Person.prototype = {
  eat: function() {
    console.log(this.name, "eat some food");
  }
};

person.say();

//person.eat(); // undefined

// var person = new Person(); // 创建实例的时候生成了一个原型对象指向{}
// Person.protype.say // 在已指向的原型对象中创建方法
// Person.prototype = {}; // 将原型重新指向了另外一个对象{}，此时person实例的proto指向保持不变。
// person.say(); // 在自身属性中寻找，没有沿着原型链继续寻找。由于还保留了proto指向，所以能够找到say。
// person.eat(); // 虽然在原型中添加了eat方法，在person实例保留的是原有的proto指向，所以找不到该方法，出现未定义。

// 要想找到eat方法需要在定义原型后实例化对象。


var person2 = new Person();

//person2.say(); // undefined

person2.eat();

// 原型结构
/*
Function.prototype = {
  constructor : Function,
  __proto__ : parent prototype,
  some prototype properties: ...
}; */
