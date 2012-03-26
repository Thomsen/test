package com.anhuioss.java.thread;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-26
 * Time: 下午4:42
 * To change this template use File | Settings | File Templates.
 */
public class ThreadMain {


    public static void main(String[] args) {

        ThreadTest tt = new ThreadTest();
        ThreadSeize ts = new ThreadSeize();

        tt.start();
        ts.start();
        

    }
    
    static class Resource {
        private int count;
        
        private static Resource res;
        
        public static Resource getInstance() {
            if (res == null) {
                return new Resource();
            }  else {
                return res;
            }
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    static class ThreadTest extends Thread {

        @Override
        public void run() {

            synchronized (this) {
                Resource r = Resource.getInstance();

                for (int i=1; i<5; i++) {
                    System.out.println(((i-1)*5) + " test set count " + i);
                    r.setCount(i);
                    try {
                        sleep(5000);

//                        wait();
                        notify();

                    } catch (InterruptedException e) {
                        Logger.getLogger("sleep execption");
                    }
                    System.out.println((i*5) + " after, thread test get count " + r.getCount());


                }
            }
            


        }
    }


    // 线程抢占
    static class ThreadSeize extends Thread {
        @Override
        public void run() {

            synchronized (this) {
                Resource r = Resource.getInstance();

                for (int i=1; i<6; i++) {
//                System.out.println("thread seize execute " + i);
                    System.out.println(((i-1)*4) + " seize set count " + i);
                    r.setCount(i);
                    try {
                        sleep(4000);

//                        notify();
                        wait();

                    } catch (InterruptedException e) {
                        Logger.getLogger("thread seize sleep exception") ;
                    }
                    System.out.println((i*4) + " after, thread seize get count " + r.getCount());

                    notify();
                }

            }

        }
    }

    // result :
//    0 test set count 1
//    0 seize set count 1
//    4 after, thread seize get count 1
//    4 seize set count 2
//    5 after, thread test get count 1      // error, count is 2
//    5 test set count 2
//    8 after, thread seize get count 2
//    8 seize set count 3
//    10 after, thread test get count 2    // error, count is 3
//    10 test set count 3
//    12 after, thread seize get count 3
//    12 seize set count 4
//    15 after, thread test get count 3    // error, count is 4
//    15 test set count 4
//    16 after, thread seize get count 4
//    16 seize set count 5
//    20 after, thread test get count 4    // error, count is 5
//    20 after, thread seize get count 5

    // add synchronized wait and notify result :    (perfect)
//    0 test set count 1
//    0 seize set count 1
//    4 after, thread seize get count 1
//    4 seize set count 2
//    8 after, thread seize get count 2
//    8 seize set count 3
//    12 after, thread seize get count 3
//    12 seize set count 4
//    16 after, thread seize get count 4
//    16 seize set count 5
//    20 after, thread seize get count 5

    // wait and notify is exchange result :
//    0 test set count 1
//    0 seize set count 1
//    5 after, thread test get count 1
//    5 test set count 2
//    10 after, thread test get count 2
//    10 test set count 3
//    15 after, thread test get count 3
//    15 test set count 4
//    20 after, thread test get count 4

}

// class, here is not static
