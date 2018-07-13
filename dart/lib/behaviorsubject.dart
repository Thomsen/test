
import 'package:rxdart/subjects.dart';


void main() {
  behavior();

  behaviorSeed();

}

void behavior() {
  final subject = new BehaviorSubject<int>();

  subject.stream.listen((i) => print("zero print: $i"));
  
  subject.add(1);
  subject.add(2);
  subject.add(3);
  
  subject.stream.listen((i) => print("first print: $i"));
  
  subject.stream.listen((i) => print("second print: $i"));
  
  subject.stream.listen((i) => print("third print: $i"));
  
}

void behaviorSeed() {
  final subject = new BehaviorSubject<int>(seedValue: 1);

  subject.stream.listen((i) => print("seed first print: $i"));
  subject.stream.listen((i) => print("seed second print: $i"));
  subject.stream.listen((i) => print("seed third print: $i"));

}