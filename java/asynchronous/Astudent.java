import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-27
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class Astudent extends Student implements ICallBack, Runnable {


    @Override
    public void run() {
        
        System.out.println(getCurrentTime() + "告诉B，让B去通知C，今晚在教室开会");
        
        // 找到b，却没找到c
        Cstudent cstu = new Cstudent();
        Bstudent bstu = new Bstudent("今晚在教室开会，请务必准时到。", cstu, this);
        
        new Thread(bstu).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getCurrentTime() + "去图书管还书");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(getCurrentTime() + "书还好了");

    }

    @Override
    public void obtain(String msg) {
        System.out.println(getCurrentTime() + "A obtain: " + msg);
    }
}
