import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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

    public static void subList() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        List<String> subList = new ArrayList<String>();
        subList = list.subList(0, 1);
        list.removeAll(subList);
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
