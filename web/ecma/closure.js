function makeAdder(a) {
  return function (b) {
    return a + b;
  };
}

var x = makeAdder(5);  // x is function and a = 5
var y = makeAdder(20); // y is function and a = 20

console.log(x(6));  // b is 6 and 5 + 6

console.log(y(7));  // b is 7 and 20 + 7

// angular1 service use closure


function newClosure(someNum, someRef) {
  var num = someNum;
  var anArray = [1, 2, 3];
  var ref = someRef;

  return function (x) {
    num += x;
    anArray.push(num);
    console.log('num: ' + num +
      '\nanArray ' + anArray.toString() +
      '\nref.someVar ' + ref.someVar);
  };
}

obj = { someVar: 4 };
fn1 = newClosure(4, obj);
fn2 = newClosure(6, obj);

fn1(1);
// num: 5 // num is 4 and 4 + 1
// anArray 1,2,3,5 // array
// ref.someVar 4  // 4

fn2(1);
// num: 7 // num is 6 and 6 + 1
// anArray 1,2,3,7 // anArray is not modifed
// ref.someVar 4 // ref property  is not modified


obj.someVar++;

fn1(2);
// num: 7  // num is 5 and 5 + 2
// anArray 1,2,3,5,7  // anArray also modified and push
// ref.someVar 5  // ref property is also modified

fn2(2);
// num: 9  // num is 7 and 7 + 2
// anArray 1,2,3,7,9  // array
// ref.someVar 5    // ref
