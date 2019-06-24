
import 'package:intl/intl.dart';

void main() {

  NumberFormat forma = NumberFormat.currency(symbol: "¥")
    ..maximumFractionDigits = 2;

  print(forma.format(13.32322));  // ￥ 13.32

  print(forma.format(132323.32322));  //


  NumberFormat forma2 = NumberFormat.currency(symbol: "¥")
    ..turnOffGrouping()
    ..maximumFractionDigits = 2;

  print(forma2.format(1300.123));  // 1300.12

}