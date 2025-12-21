package solution.interview.models;

/**
 * Abstract Vehicle class demonstrating Abstraction
 */
public abstract class Vehicle {
    protected String brand;
    protected int year;
    
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    
    public abstract void start();
    
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}