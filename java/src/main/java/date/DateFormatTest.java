package date;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateFormatTest {
    public static void main(String args[]) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MM");

        try {
            Date date = dateFormat.parse("0001-01-03");
            System.out.println("date " + date.toString());
            System.out.println("date millis " + date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}