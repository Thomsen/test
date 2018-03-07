package thread;

import com.sun.deploy.util.StringUtils;
import sun.swing.StringUIClientPropertyKey;
import thread.RunnableTest;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-26
 * Time: 下午5:52
 * To change this template use File | Settings | File Templates.
 */
public class RunnableMain {
    
    public static void main(String[] args) {
        
        RunnableTest rt = new RunnableTest("threadA");

        Thread threadA = new Thread(rt);
        threadA.start();

        RunnableTest rtb = new RunnableTest("threadB");

        Thread threadB = new Thread(rtb);
        threadB.start();

    }


}



