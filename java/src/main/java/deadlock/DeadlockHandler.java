package deadlock;

import java.lang.management.ThreadInfo;

public interface DeadlockHandler {
    void handleDeadlock(final ThreadInfo[] deadlockedThreads);
}

