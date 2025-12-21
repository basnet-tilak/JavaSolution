package solution.interview.designpatterns;

/**
 * Dog implementation of Animal interface
 */
public class Dog implements Animal {
    public void makeSound() { 
        System.out.println("Woof! Woof!"); 
    }
    
    public String getType() { 
        return "Dog"; 
    }
}