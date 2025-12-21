package solution.interview.exceptions;

import java.util.Scanner;

/**
 * Exception Handling Examples
 */
public class ExceptionHandling {
    
    public static void demonstrateExceptionHandling() {
        System.out.println("=== Exception Handling ===");
        
        try {
            BankAccount account = new BankAccount("ACC123", 1000.0);
            account.withdraw(500.0);
            account.withdraw(600.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught InsufficientFundsException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: " + e.getMessage());
        } finally {
            System.out.println("Transaction completed");
        }
    }
    
    public static void demonstrateTryWithResources() {
        System.out.println("\n=== Try-with-Resources ===");
        
        String data = "Line 1\nLine 2\nLine 3";
        
        try (Scanner scanner = new Scanner(data)) {
            while (scanner.hasNextLine()) {
                System.out.println("Read: " + scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }
    
    static void main() {
        demonstrateExceptionHandling();
        demonstrateTryWithResources();
    }
}