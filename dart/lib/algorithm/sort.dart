
main () {

  printData(sortBubble(getData()));

  printData(sortSelect(getData()));

  printData(sortInsert(getData()));

}

getData() {
  return [1, 3, 4, 2, 8, 28, 22, 12, 6];
}

printData(List datas) {
  if (null != datas) {
    print(' ---- ${datas.toString()}');
  }
}

/// 冒泡排序
sortBubble(List datas) {
  if (null != datas) {
    int size = datas.length;
    int tmp;
    for (int j=0; j<size; j++) {
      for (int i = size - 1; i > j; i--) {
        if (datas[i] < datas[i - 1]) {
          tmp = datas[i];
          datas[i] = datas[i - 1];
          datas[i - 1] = tmp;
        }
      }
    }
  }
  return datas;
}

/// 选择性排序
sortSelect(List datas) {
  if (null != datas) {
    int size = datas.length;
    int min;
    int index = 0;
    int tmp;
    for (int j=0; j<size; j++) {
      min = datas[j];
      index = j;
      for (int i=j; i<size-1; i++) {
        if (min > datas[i+1]) {
          min = datas[i+1];
          index = i+1;
        }
      }
      if (index != j) {
        tmp = datas[j];
        datas[j] = min;
        datas[index] = tmp;
      }
    }
  }

  return datas;
}


/// 插入排序
sortInsert(List datas) {
  if (null != datas) {
    int size = datas.length;
    int tmp;
    for (int j=1; j<size; j++) {
      for (int i=0; i<j; i++) {
        if (datas[j] < datas[i]) {
          tmp = datas[i];
          datas[i] = datas[j];
          datas[j] = tmp;
        }
      }
    }
    return datas;
  }
}
