package solution.interview.multithreading;

/**
 * Thread-safe counter for synchronization examples
 */
public class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized void decrement() {
        count--;
    }
    
    public int getCount() {
        return count;
    }
}