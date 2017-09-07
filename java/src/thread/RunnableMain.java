
/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-26
 * Time: 下午5:52
 * To change this template use File | Settings | File Templates.
 */
public class RunnableMain {
    
    public static void main(String[] args) {
        
        RunnableTest rt = new RunnableTest();
        
        new Thread(rt).start();

    }
}

class RunnableTest implements  Runnable {

    @Override
    public void run() {

        for (int i=0; i<5; i++) {
            System.out.println("runnable test " + i);

            // need Thread
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("interrupted exception");
            }
        }

    }
}


