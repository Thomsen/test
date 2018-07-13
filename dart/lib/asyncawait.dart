
import 'dart:async';

main() async {

  foo().then((i) {
    print("async i $i");
  });

  fut().then((t) {
    print("future t $t");
  });

 
  final a = await fooa();  // main method need async
  print("await a $a");

}

foo() async => 24;


fut() => new Future(() => 29);


Future<String> fooa() async {
  return new Future.delayed(const Duration(seconds: 1),
     () => "future");
}