package solution.test_java;

public class PairCalculator {

    public static int countPairs(int n) {
        if (n < 2) {
            return 0;
        } else {
            return n * (n - 1) / 2;
        }
    }

    public static void main(String[] args) {
        int n = 4; // Test with input n = 4
        System.out.println("Number of pairs for " + n + " players: " + countPairs(n));
    }
}
