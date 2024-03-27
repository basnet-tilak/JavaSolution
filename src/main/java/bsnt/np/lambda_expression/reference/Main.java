package bsnt.np.lambda_expression.reference;

public class Main {
    public static void main(String[] args) {
        Square s = new Square(4);
        Shape shape = Square::calculateArea;
        System.out.println("Area of Square: " +shape.getArea(s));
    }
}
