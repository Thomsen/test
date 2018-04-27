import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/**
 * Describe class Concurrent here.
 *
 *
 * Created: Thu Jun 15 08:41:45 2017
 *
 * @author <a href="mailto:Thomsen@PC-20170515DRHG"></a>
 * @version 1.0
 */
public class Concurrent {

    public Concurrent() {

    }

    public static final void main(final String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Concurrent con = new Concurrent();
        IntStream.range(0, 10000)
            .forEach(i -> executor.submit(con::increment));

        IntStream.range(0, 10000)
            .forEach(i -> executor.submit(con::incrementSync));

        IntStream.range(0, 10000)
            .forEach(i -> executor.submit(con::incrementLock));

        IntStream.range(0, 10000)
            .forEach(i -> executor.submit(con::incrementStamped));

         IntStream.range(0, 10000)
             .forEach(i -> executor.submit(con::incrementAtomic));

         IntStream.range(0, 10000)
             .forEach(i -> executor.submit(con::incrementAdder));

         IntStream.range(0, 10)
             .forEach(i -> executor.submit(con::concurrentMap));

        stop(executor);

        System.out.println(con.count);

    }


    int count = 0;

    void increment() {
        System.out.println("increment " + count);
        count = count + 1;
    }

    synchronized void incrementSync() {
        System.out.println("concurrent synchronized " + count);
        count ++;
    }

    ReentrantLock lock = new ReentrantLock();

    void incrementLock() {
        System.out.println("concurrent renntrant start " + count);
        lock.lock();
        System.out.println("concurrent renntrant lock " + count);
        try {
            count ++;
        } finally {
            lock.unlock();
        }
    }

    // 8
    StampedLock stampedLock = new StampedLock();

    void incrementStamped() {
        System.out.println("concurrent stamped start " + count);
        long stamp = stampedLock.writeLock();
        System.out.println("concurrent stamped lock " + count);
        try {
            count ++;
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    AtomicInteger atomicInt = new AtomicInteger(0);

    void incrementAtomic() {
        atomicInt.incrementAndGet();
        // atomicInt.updateAndGet(count -> count ++ );
        System.out.println("atomic integer count " + count);
        System.out.println("atomic integer value " + atomicInt.get());
    }

    LongAdder longAdder = new LongAdder();

    void incrementAdder() {
        longAdder.increment();
        System.out.println("long adder value " + longAdder.intValue());
    }


    // ConcurrentMap

    ConcurrentMap<String, String> coMap = new ConcurrentHashMap<String, String>();
    void concurrentMap() {
        longAdder.increment();
        coMap.put("foo", "foo " + longAdder.intValue());

        coMap.putIfAbsent("absent", "absent " + longAdder.intValue());

        coMap.forEach((key, value) ->
                      System.out.printf("%s = %s \n", key, value));
    }

    public static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!executor.isTerminated()) {
                System.out.println("killing non-finised tasks");
            }
            executor.shutdownNow();
        }
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
