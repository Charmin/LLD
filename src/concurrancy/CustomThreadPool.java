package concurrancy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {

    public static void main(String[] args) {
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(3);
        customThreadPoolExecutor.addTask(() -> {
            System.out.println("Task 1");
        });
        customThreadPoolExecutor.addTask(() -> {
            System.out.println("Task 2");
        });
        customThreadPoolExecutor.addTask(() -> {
            System.out.println("Task 3");
        });
        customThreadPoolExecutor.addTask(() -> {
            System.out.println("Task 4");
        });
        customThreadPoolExecutor.addTask(() -> {
            System.out.println("Task 5");
        });
        customThreadPoolExecutor.addTask(() -> {
            System.out.println("Task 6");
        });
    }
}

/**
 * you can add more attributes like
 *
 * maxThreadPoolSize  - maximum no of threads that can stay alive
 * coreThreadPoolSize - minimum no of threads to be active always in the pool, irrespective of the keep alive time.
 * keepAliveTime      - threads
 *
 */
class CustomThreadPoolExecutor {
    BlockingQueue<Runnable> workerQueue;
    Thread[] threads;

    public CustomThreadPoolExecutor(int poolSize) {
        workerQueue = new LinkedBlockingQueue<>();
        threads = new Thread[poolSize];
        int i = 0;
        for (Thread t : threads) {
            t = new Worker("Pool-thread "+i);
            i++;
            t.start();
        }
    }

    public void addTask(Runnable runnable) {
        try {
            workerQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Worker extends Thread {
        public Worker(String name) {
            this.setName(name);
        }

        public void run() {
            while(true) {
                try {
                    System.out.println("Current executor "+ this.getName());
                    workerQueue.take().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
