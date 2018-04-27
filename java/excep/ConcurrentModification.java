import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;
/**
 * Describe class ConcurrentModification here.
 *
 *
 * Created: Tue Jun 13 10:30:39 2017
 *
 * @author <a href="mailto:Thomsen@PC-20170515DRHG"></a>
 * @version 1.0
 */
public class ConcurrentModification {

    public ConcurrentModification() {

    }

    public static final void main(final String[] args) {

        subList();

        singleThread();

        // multiThread();
    }

    static List<String> subList = new CopyOnWriteArrayList<String>();

    public static void subList() {
        // List<String> list = new ArrayList<String>();
        List<String> list = new CopyOnWriteArrayList<String>();
        list.add("a");
        list.add("b");

        // list = Collections.unmodifiableList(list);

        // List<String> subList = new ArrayList<String>();

        subList = list.subList(0, 1);

        // list.removeAll(subList);

        // list.add("c");

        synchronized(list) {
            // list.clear();  // if unmodifiableList, UnsupportedOperationException
        }

        Semaphore semaphore = new Semaphore(2);

        new Thread("modify list") {
            public void run() {
                synchronized(list) {
                    try {
                        semaphore.acquire();
                        Thread.sleep(3000);
                    } catch (Exception e) {
                    }
                    list.clear();
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " finished.");
                }
            }
        }.start();

        System.out.println("list size: " + list.size());
        // System.out.println("subList size: " + subList.size());  // concurrent modification exception


        new Thread("iteator list") {
            public void run() {
                // synchronized(list) {  // 加同步的话，会等到modify list thread execute finish
                    try {
                        // one semaphore lock resource, wait modify list thread. set two semaphore
                        semaphore.acquire();
                        Thread.sleep(599);
                    } catch (Exception e) {
                    }
                    for (String s : subList) {  // also exception

                    }
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " finished.");

                // }
            }
        }.start();

    }


    public static void singleThread() {
        List<String> conList = new ArrayList<String>();

        for (int i=0; i<7; i++) {
            conList.add("" + i);
        }

        Iterator<String> iter = conList.iterator();

        while(iter.hasNext()) {
            String str = iter.next();
            System.out.println("conList value: " + str);
            if ("3".equals(str)) {
                // conList.remove(str);   // 导致 next() ConcurrentModificationException
                iter.remove();
            }
            // not ConcurrentModificationException 原因在于下一次hasNext为false
            if ("5".equals(str)) {
                conList.remove(str);
            }
        }

        List<String> conCopyList = new CopyOnWriteArrayList<String>();
        for (int j=11; j<17; j++) {
            conCopyList.add("" + j);
        }

        Iterator<String> copyIter = conCopyList.iterator();
        while(copyIter.hasNext()) {
            String str = copyIter.next();
            System.out.println("conCopyList value: " + str);
            if ("13".equals(str)) {
                conCopyList.remove(str);
            }
        }

        Map<String, String> conMap = new HashMap<String, String>();
        conMap.put("1", "1");
        conMap.put("2", "2");
        conMap.put("3", "3");

        Iterator<String> iterMap = conMap.keySet().iterator();
        while(iterMap.hasNext()) {
            String key = iterMap.next();
            System.out.println("conMap value: " + conMap.get(key));
            if ("2".equals(key)) {
                conMap.put("1", "4");
                // conMap.put("4", "4");  // 导致 next() ConcurrentModificationException
            }
            System.out.println("conMap value after: " + conMap.get(key));
        }
    }

    private static void multiThread() {
        List<String> conList = new ArrayList<String>();
        for (int i=0; i<10; i++) {
            conList.add("" + i);
        }

        new Thread() {
            public void run() {
                Iterator<String> iterator = conList.iterator();

                synchronized (conList) {
                    while (iterator.hasNext()) {
                        System.out.println(Thread.currentThread().getName() + ":"
                                           + iterator.next());
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

        new Thread() {
            public synchronized void run() {
                Iterator<String> iterator = conList.iterator();

                synchronized (conList) {
                    while (iterator.hasNext()) {
                        String element = iterator.next(); // ConcurrentModificationException 迭代前增加同步锁，或采用CopyOnWriteArray
                        System.out.println(Thread.currentThread().getName() + ":"
                                           + element);
                        if (element.equals("3")) {
                            iterator.remove();
                        }
                    }
                }
            }
        }.start();
    }

}
