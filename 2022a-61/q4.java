//a.
//2

//b.
// כן, מכיוון שהמתודה תיהיה זמינה עבור כל אובייקט ולא מתודה כללית, ואז כל אובייקט יקבל מנעול משלו בכניסה לSYNC, אזי כל התרדים היו יכולים לרוץ עליה במקביל.

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkThread {
    private int[] vec;
    private int id;
    private int result;
    private static int shouldBePrinting = 0;
    private static final Object monitor = new Object();
    private static Lock lock = new ReentrantLock();
    private static Condition cond = lock.newCondition();

    public static synchronized int process(int[] vec, int id) {
        int result = 0;
        for (int i = 0; i < vec.length; i++) {
            vec[i] = vec[i] + 1;
            result = result + vec[i];
        }
        return result;
    }

    // Synchronized
    public void printResultSync() {
        synchronized (monitor) {
            while (shouldBePrinting != id)
                try {
                    monitor.wait();
                } catch (Exception e) {
                    System.out.print(e);
                }
        }
        System.out.print("task" + id + "result = " + result);
        shouldBePrinting++;
        monitor.notifyAll();
    }

    // using locks
    public synchronized void printResultLocks() {
        lock.lock();
        try {
            while (id != shouldBePrinting) {
                cond.await();
            }
            System.out.print("task" + id + "result = " + result);
            shouldBePrinting++;
            cond.signalAll();

        } catch (Exception e) {
            System.out.print(e);
        } finally {
            lock.unlock();
        }
    }

    public void run() {
        result = process(vec, id);
        printResultSync();
    }
}