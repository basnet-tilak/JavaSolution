package solution.interview.designpatterns;

/**
 * Cat implementation of Animal interface
 */
public class Cat implements Animal {
    public void makeSound() { 
        System.out.println("Meow! Meow!"); 
    }
    
    public String getType() { 
        return "Cat"; 
    }
}