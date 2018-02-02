package thread;

import java.util.concurrent.CountDownLatch;

public class ResourceThead {

    public static void main(String[] args) {

        test();

        testThreadLocal();
    }

    private static void test() {
        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        ResourceContext resourceContext = new ResourceContext();
        for (int i=1; i<=threads; i++) {
            new Thread("thread - " + i) {
                @Override
                public void run() {
                    super.run();

                    for (int j=0; j<4; j++) {
                        resourceContext.addCounter(String.valueOf(j));
                        resourceContext.printCounter();
                    }

                    resourceContext.setCounter(getName() + " thread end ");
                    countDownLatch.countDown();
                }
            }.start();

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testThreadLocal() {
        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        ResourceContext resourceContext = new ResourceContext();
        for (int i=1; i<=threads; i++) {
            new Thread("threadLocal - " + i) {
                @Override
                public void run() {
                    super.run();

                    for (int j=0; j<4; j++) {
                        resourceContext.add(String.valueOf(j));
                        resourceContext.print();
                    }

                    resourceContext.set(getName() + " threadLocal end ");
                    countDownLatch.countDown();
                }
            }.start();

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
