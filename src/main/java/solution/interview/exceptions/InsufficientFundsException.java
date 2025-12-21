package solution.interview.exceptions;

/**
 * Custom checked exception for insufficient funds
 */
public class InsufficientFundsException extends Exception {
    private final double amount;
    
    public InsufficientFundsException(double amount) {
        super("Insufficient funds. Required: " + amount);
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}