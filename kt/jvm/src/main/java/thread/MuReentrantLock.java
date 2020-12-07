package thread;

public class MuReentrantLock {

    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockedCount = 0;

    public synchronized void lock() throws  InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && !lockedBy.equals(thread)) {
            wait();
        }
        isLocked = true;
        lockedCount ++;
        lockedBy = thread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread().equals(this.lockedBy)) {
            lockedCount --;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
