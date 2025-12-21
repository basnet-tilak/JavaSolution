package solution.interview.algorithms;

/**
 * Mathematical Algorithm Examples
 */
public class MathematicalAlgorithms {
    
    /**
     * Q: Check if an integer is a palindrome without converting to string.
     * Input: x = 121
     * Output: true
     */
    public static boolean isPalindromeNumber(int x) {
        if (x < 0) return false;
        
        int original = x, reversed = 0;
        while (x > 0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        
        return original == reversed;
    }
    
    /**
     * Q: Convert a Roman numeral to an integer.
     * Input: s = "III"
     * Output: 3
     */
    public static int romanToInt(String s) {
        int[] values = new int[128];
        values['I'] = 1; values['V'] = 5; values['X'] = 10;
        values['L'] = 50; values['C'] = 100; values['D'] = 500; values['M'] = 1000;
        
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && values[s.charAt(i)] < values[s.charAt(i + 1)]) {
                result -= values[s.charAt(i)];
            } else {
                result += values[s.charAt(i)];
            }
        }
        
        return result;
    }
    
    /**
     * Q: Convert an integer to a Roman numeral.
     * Input: num = 58
     * Output: "LVIII"
     */
    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result.append(symbols[i]);
                num -= values[i];
            }
        }
        
        return result.toString();
    }
    
    /**
     * Q: Compute and return the square root of x (integer part only).
     * Input: x = 8
     * Output: 2
     */
    public static int mySqrt(int x) {
        if (x == 0) return 0;
        
        int left = 1, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return right;
    }
    
    /**
     * Q: Implement pow(x, n), which calculates x raised to the power n.
     * Input: x = 2.0, n = 10
     * Output: 1024.0
     */
    public static double myPow(double x, int n) {
        if (n == 0) return 1.0;
        
        long longN = n;
        if (longN < 0) {
            x = 1 / x;
            longN = -longN;
        }
        
        double result = 1.0;
        while (longN > 0) {
            if (longN % 2 == 1) {
                result *= x;
            }
            x *= x;
            longN /= 2;
        }
        
        return result;
    }
    
    /**
     * Q: Calculate the factorial of a number.
     * Input: n = 5
     * Output: 120
     */
    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
    
    /**
     * Q: Check if a number is prime.
     * Input: n = 17
     * Output: true
     */
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Q: Check if a number is a happy number (sum of squares of digits eventually equals 1).
     * Input: n = 19
     * Output: true (1² + 9² = 82, 8² + 2² = 68, 6² + 8² = 100, 1² + 0² + 0² = 1)
     */
    public static boolean isHappy(int n) {
        int slow = n, fast = n;
        
        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        } while (slow != fast);
        
        return slow == 1;
    }
    
    private static int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
    
    /**
     * Q: Check if a number is a power of two.
     * Input: n = 16
     * Output: true
     */
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Mathematical Algorithms ===");
        
        System.out.println("121 is palindrome: " + isPalindromeNumber(121));
        System.out.println("Roman III to int: " + romanToInt("III"));
        System.out.println("Int 4 to roman: " + intToRoman(4));
        System.out.println("Square root of 8: " + mySqrt(8));
        System.out.println("2^10: " + myPow(2.0, 10));
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("17 is prime: " + isPrime(17));
        System.out.println("19 is happy number: " + isHappy(19));
        System.out.println("16 is power of 2: " + isPowerOfTwo(16));
    }
}