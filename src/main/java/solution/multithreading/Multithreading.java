package solution.multithreading;

//This is a main class of thread
public class Multithreading {
    public static void main(String[] args) throws InterruptedException {
        int n = 8; // Number of threads
        for (int i = 0; i < n; i++) {
            MultithreadingDemo object = new MultithreadingDemo();
            object.start();
        }
        System.out.println("------------ Runnable thread is running: -------------- ");
        for (int i = 0; i < n; i++) {
            Thread object = new Thread(new ThreadRunnable());
            object.start();
        }
    }
}
