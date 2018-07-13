
import 'package:rxdart/rxdart.dart';
import 'package:rxdart/subjects.dart';


void main() {

  final subject = new PublishSubject<int>();

  subject.stream.listen( (i) {
    print("first print: $i");
  });
  
  subject.add(1);
  subject.add(2);

  subject.stream.listen((i) => print("second print: $i"));

  subject.add(3);

  subject.stream.listen((i) => print("third print: ")); 

  subject.close();

}