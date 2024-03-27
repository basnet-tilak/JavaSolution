package bsnt.np.lambda_expression.interfaces;

public class Main {
    public static void main(String[] args) {
        // this is an implementation of the functional interface process
        // this is process of anonymous class
        GreetingMessage gm = new GreetingMessage() {
            @Override
            public void greet(String name) {
                System.out.println("My name is :" +name);
            }
        };
        gm.greet("Tilak Basnet");

        // Interface is implementation using the lambda expression. which is same as above implementation.
        GreetingMessage gm2 = name -> System.out.println("My name is :" +name);
        gm2.greet("Tilak Basnet");

        // ------------We can implementation the own logic inside the lambda expression--------- //
        MethodWithMultiParams methodWithMultiParams = (name, age) -> {
            int a = age + 2;// age increase by 2
            System.out.println("My name is " + name + ",  Age is: " + a);
        };
        methodWithMultiParams.getData("Tilak", 31);
    }
}
