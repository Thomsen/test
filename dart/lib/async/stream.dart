Future<int> sumStream(Stream<int> stream) async {
  var sum = 0;
  await for (final value in stream) {
    sum += value;
  }
  return sum;
}

Stream<int> countStream(int to) async* {
  for (int i = 1; i <= to; i++) {
    yield i;
  }
}

void main() async {
  var stream = countStream(10);
  var sum = await sumStream(stream);
  print(sum); // 55

  for (int i=1; i<=3; i++) {
    print(i);
    var s = await sumClac(i);
    print(s);
  }
}

Future<int> sumClac(int n) async {
  var sum = 0;
  for (int i=1; i<= n; i++) {
    sum += i;
  }
  return sum;
}