package solution.interview.models;

/**
 * Bike class demonstrating Polymorphism with interface implementation
 */
public class Bike extends Vehicle implements Drivable {
    public Bike(String brand, int year) {
        super(brand, year);
    }
    
    @Override
    public void start() {
        System.out.println(brand + " bike ready to ride!");
    }
    
    @Override
    public void drive() {
        System.out.println("Riding the bike");
    }
    
    @Override
    public void stop() {
        System.out.println("Bike stopped");
    }
}