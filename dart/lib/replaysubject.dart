
import 'package:rxdart/subjects.dart';

void main() {
  replay();

  replayMax();
}

void replay() {
  final subject = new ReplaySubject<int>();

  subject.stream.listen((i) => print("zero print: $i"));
  
  subject.add(1);
  subject.add(2);
  subject.add(3);
  
  subject.stream.listen((i) => print("first print: $i"));
  subject.stream.listen((i) => print("second print: $i"));
  subject.stream.listen((i) => print("third print: $i"));
  
}

void replayMax() {
  final subject = new ReplaySubject<int>(maxSize: 2);

  subject.add(1);
  subject.add(2);
  subject.add(3);
  
  subject.stream.listen((i) => print("max first print: $i"));
  subject.stream.listen((i) => print("max second print: $i"));
  subject.stream.listen((i) => print("max third print: $i"));
}