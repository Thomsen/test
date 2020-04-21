package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolSync {


    static class ThreadResource {

        List<String> resources = new ArrayList<String>();

        public synchronized void addResource(String res) {
            resources.add(res);
        }

        public synchronized void printResource() {
            synchronized (resources) {
                for (String res : resources) {
                    System.out.println(res);
                }
            }
            System.out.println("print res");
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newScheduledThreadPool(3);
        ThreadResource resource = new ThreadResource();

        for (int i=0; i<5; i++) {
            executor.execute(() -> resource.addResource("data"));
        }

        executor.execute(() -> resource.printResource());

        System.out.println("end");
    }

}
