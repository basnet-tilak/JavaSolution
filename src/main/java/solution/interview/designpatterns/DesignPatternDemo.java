package solution.interview.designpatterns;

/**
 * Design Patterns Demo
 */
public class DesignPatternDemo {
    
    public static void main() {
        System.out.println("=== Design Patterns Examples ===");
        
        // Singleton Pattern
        System.out.println("\n1. Singleton Pattern:");
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println("Same instance: " + (s1 == s2));
        s1.showMessage();
        
        // Factory Pattern
        System.out.println("\n2. Factory Pattern:");
        Animal dog = AnimalFactory.createAnimal("dog");
        Animal cat = AnimalFactory.createAnimal("cat");
        System.out.println("Created " + dog.getType() + ": ");
        dog.makeSound();
        System.out.println("Created " + cat.getType() + ": ");
        cat.makeSound();
    }
}