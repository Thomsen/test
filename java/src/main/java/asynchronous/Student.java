package asynchronous;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-27
 * Time: 下午3:28
 * To change this template use File | Settings | File Templates.
 */
public class Student {


    public String getCurrentTime() {
        return new Date(System.currentTimeMillis()) + " :　";
    }
}
