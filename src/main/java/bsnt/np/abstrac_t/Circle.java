package bsnt.np.abstrac_t;

public class Circle extends Shape{
    private final double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
    public static void main(String[] args) {
        Circle circle = new Circle(30);
        System.out.println("Area of circle: " +circle.area());
    }
}
