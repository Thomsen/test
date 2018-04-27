package thread;

public class RunnableTest implements Runnable {

    private String threadName;

    public RunnableTest(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {

        for (int i=0; i<5; i++) {
            System.out.println(threadName + " runnable test " + i);

            // need Thread
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("interrupted exception");
            }
        }

    }


}
