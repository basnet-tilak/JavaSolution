package bsnt.np.lambda_expression.reference;

public class Square {
    int sideLength;

    public Square(int side) {
        this.sideLength = side;
    }

    public int calculateArea(){
        return sideLength * sideLength;
    }
}
