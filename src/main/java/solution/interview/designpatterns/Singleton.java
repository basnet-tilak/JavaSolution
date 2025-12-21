package solution.interview.designpatterns;

/**
 * Thread-safe Singleton pattern
 */
public class Singleton {
    private static volatile Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    
    public void showMessage() {
        System.out.println("Singleton instance created!");
    }
}