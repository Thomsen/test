
import 'dart:async';

import 'dart:isolate';

void main() {
    // testTask2();
    // singleTask();
    futureTask('msg');

    spawnIsolate();
}

void log(msg) => print(msg);

void spawnIsolate() {
  Isolate.spawn(log, "hello isolate");
}

void futureTask(msg) {
  Future(() => print('f1'));

  Future fx = Future(() => null);

  Future(() => print('f2')).then((_) {
    print('f3');
    scheduleMicrotask(() => print('f4'));
  }).then((_) => print('f5'));

  Future(() => print('f6'))
    .then((_) => Future(() => print('f7')))
    .then((_) => print('f8'));

  Future(() => print('f9'));

  fx.then((_) => print('f10'));

  scheduleMicrotask(() => print('f11'));

  print('f12');

  // 单个 Future
  // 函数体放入EQ；外部后续代码继续同步执行；EeventLoop按序取出Event，同步执行相应链路

  // 多个 Future
  // 依次加入Event Queue的先后顺序
  // then执行体的Future执行体完毕后立即同步执行
  // 多个then方法执行体按链式调用有序执行
  // 若Future执行体已经完毕，后续加入的then执行体则放入MicroTask Queue

  // f12
  // f11
  // f1
  // null f10
  // f2 f3 f5 f4
  // f6
  // f9
  // f7 f8


  Future(() => print('f13'))
    .then((_) async => await Future(() => print('f14')))
    .then((_) => print('f15'));

  // f13 f14 f15

  Future(() => print('f16'))
    .then((_) async => await scheduleMicrotask(() => print('f17')))
    .then((_) => print('f18'));

  // f16 f17 f18
  // f13 f16 f17 f18 f14 f15

}

void  singleTask() {

  print("Adding code to the stack");
  Timer(Duration(seconds:0), () {
    print('Anonymous function running next code from queue');
  });

  b(y) {
    print("b() frame added to stack");
    print("Value passed in is $y");
    print("b() frame removed from stack");
  }

  a(x) {
    print("a() frame added to stack");
    b(x);
    print("a() frame removed from stack");
  }

  a(42);
  print("Ending work for this stack");

}

void testTask1() {
  print('main #1 of 2');
  scheduleMicrotask(() => ('microtask #1 of 2'));

  new Future.delayed(new Duration(seconds: 1),
    () => print('future #1 (delayed)'));

  new Future(() => print('future #2 of 3'));

  new Future(() => print('future #3 of 3'));

  scheduleMicrotask(() => print('microtask #2 of 2'));

  print('main #2 of 2');
}

void testTask2() {
  print('main #1 of 2');
  scheduleMicrotask(() => print('microtask #1 of 3'));

  new Future.delayed(new Duration(seconds:1),
      () => print('future #1 (delayed)'));

  new Future(() => print('future #2 of 4'))
      .then((_) => print('future #2a'))
      .then((_) {
        print('future #2b');
        scheduleMicrotask(() => print('microtask #0 (from future #2b)'));
      })
      .then((_) => print('future #2c'));

  scheduleMicrotask(() => print('microtask #2 of 3'));

  new Future(() => print('future #3 of 4'))
      .then((_) => new Future(
                   () => print('future #3a (a new future)')))
      .then((_) => print('future #3b'));

  new Future(() => print('future #4 of 4'));
  scheduleMicrotask(() => print('microtask #3 of 3'));
  print('main #2 of 2');
}
