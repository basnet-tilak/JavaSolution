package solution.interview.models;

/**
 * Car class demonstrating Encapsulation and Inheritance
 */
public class Car extends Vehicle {
    private String model;
    private double price;
    
    public Car(String brand, int year, String model, double price) {
        super(brand, year);
        this.model = model;
        this.price = price;
    }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price > 0) this.price = price;
    }
    
    @Override
    public void start() {
        System.out.println(brand + " " + model + " started!");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Model: " + model + ", Price: $" + price);
    }
}