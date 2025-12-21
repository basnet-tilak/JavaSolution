package solution.interview.multithreading;

import java.util.concurrent.*;

/**
 * Multithreading Examples
 */
public class MultithreadingExamples {
    
    public static void demonstrateThreadCreation() throws InterruptedException {
        System.out.println("=== Thread Creation ===");
        
        MyThread thread1 = new MyThread("Thread-1");
        Thread thread2 = new Thread(new MyRunnable("Thread-2"));
        
        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
    }
    
    public static void demonstrateSynchronization() throws InterruptedException {
        System.out.println("\n=== Synchronization ===");
        
        Counter counter = new Counter();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Final counter value: " + counter.getCount());
    }
    
    public static void demonstrateExecutorFramework() {
        System.out.println("\n=== Executor Framework ===");
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by " + 
                                 Thread.currentThread().getName());
            });
        }
        
        executor.shutdown();
    }
    
    static void main() throws InterruptedException {
        demonstrateThreadCreation();
        demonstrateSynchronization();
        demonstrateExecutorFramework();
    }
}