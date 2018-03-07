package ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thom on 7/9/2017.
 */
public class ListTest {

    public static void main(String[] args) {

        ArrayList arr = new ArrayList();
        for (int i=1; i<21; i++) {
            arr.add(i);
        }

        arr.stream().forEach(System.out::print);

        System.out.println();

        List subArr = arr.subList(0, 20);  // toIndex 上限不可达

        subArr.stream().forEach(System.out::print);

    }
}
