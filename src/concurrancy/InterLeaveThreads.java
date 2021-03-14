package concurrancy;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Print odd and even alternatively
 * */
public class InterLeaveThreads {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition print = lock.newCondition();
        Thread odd = new Thread(new OddEvenMonitor("odd", lock, print, 10));
        Thread even = new Thread(new OddEvenMonitor("even", lock, print, 10));
        System.out.println("Main " + Thread.currentThread());
        odd.start();
        even.start();
    }
}

class OddEvenMonitor implements Runnable {

    private static AtomicBoolean oddTurn = new AtomicBoolean(true);
    private String type;
    private ReentrantLock lock;
    private Condition condition;
    private int limit;

    public OddEvenMonitor(String type, ReentrantLock lock, Condition condition, int limit) {
        this.type = type;
        this.lock = lock;
        this.condition = condition;
        this.limit = limit;
    }

    public void printOdd(int num) {
        try {
            lock.lock();
            System.out.println("Locked! " + Thread.currentThread());
            while (!oddTurn.get()) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(type + " - " + num);
            oddTurn.set(false);
            condition.signalAll();
        } finally {
            System.out.println("UnLocked! " + Thread.currentThread());
            lock.unlock();
        }
    }

    public void printEven(int num) {
        try {
            lock.lock();
            while (oddTurn.get()) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(type + " - " + num);
            oddTurn.set(true);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        int seed = type.equals("odd") ? 1 : 2;
        while (seed < limit) {
            if (type.equals("odd")) {
                printOdd(seed);
            } else {
                printEven(seed);
            }
            seed += 2;
        }
    }

}