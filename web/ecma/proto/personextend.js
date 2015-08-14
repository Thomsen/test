function Person() {

}

Person.prototype.name = "thom";
Person.prototype.say = function() {
  console.log("hi,", this.name);
};

var person = new Person();
person.say();

function Child() {

}

Child.prototype = new Person();
Child.prototype.eat = function() {
  console.log(this.name, 'eat some food');
};

var child = new Child();
child.name = 'my son';
child.say();
child.eat();


var person2 = new Person();
person2.say(); // 'hi some'
person2.eat(); // undefined

// 在angularjs中，ion-content有自身的scope，ng-model的值会赋给本身的scope。在ion-view中定义的controller，通过$scope取不到model的值，报undefined。ion-content的scope会自动继承controller中的$scope。
