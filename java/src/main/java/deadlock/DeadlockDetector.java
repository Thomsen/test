package deadlock;

import java.lang.management.ThreadInfo;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class DeadlockDetector {

    private final DeadlockHandler deadlockHandler;

    private final long period;

    private final TimeUnit unit;

    private final ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    final Runnable deadlockCheck = new Runnable() {
            public void run() {
                long[] deadlockedThreadlds = DeadlockDetector.this.mbean.findDeadlockedThreads();
                if (deadlockedThreadlds != null) {
                    ThreadInfo[] threadInfos = DeadlockDetector.this.mbean.getThreadInfo(deadlockedThreadlds);
                    DeadlockDetector.this.deadlockHandler.handleDeadlock(threadInfos);
                }
            }
    };

    public DeadlockDetector(final DeadlockHandler deadlockHandler, final long period, final TimeUnit unit) {
        this.deadlockHandler = deadlockHandler;
        this.period = period;
        this.unit = unit;
    }


    public void start() {
        this.scheduler.scheduleAtFixedRate(this.deadlockCheck, this.period, this.period, this.unit);
    }
}