package solution.interview.models;

/**
 * SportsCar class demonstrating Inheritance
 */
public class SportsCar extends Car {
    private final int topSpeed;
    
    public SportsCar(String brand, int year, String model, double price, int topSpeed) {
        super(brand, year, model, price);
        this.topSpeed = topSpeed;
    }
    
    @Override
    public void start() {
        System.out.println("Sports car engine roaring!");
    }
    
    public void turboBoost() {
        System.out.println("Turbo activated! Top speed: " + topSpeed);
    }
}