

import 'package:rxdart/rxdart.dart';

import 'dart:async';  // Stream


void main() {
  create();

  just();
}

void create() {
  new Observable(new Stream.fromIterable([1, 2]))
    .interval(new Duration(seconds: 2))
    .flatMap((i) => new Observable.just([6, 7, 8]))
    .take(1)
    .listen(print);
}

void just() {
  new Observable.just([6, 7, 8])
    .listen( (i) => print(i) );
}