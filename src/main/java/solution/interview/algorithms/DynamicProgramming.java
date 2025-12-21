package solution.interview.algorithms;

import java.util.Arrays;

/**
 * Dynamic Programming Interview Questions
 */
public class DynamicProgramming {
    
    /**
     * Q: Calculate the nth Fibonacci number
     * Input: n = 10
     * Output: 55
     */
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    
    /**
     * Q: Count ways to climb stairs (1 or 2 steps at a time)
     * Input: n = 3
     * Output: 3 (ways: 1+1+1, 1+2, 2+1)
     */
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
    
    /**
     * Q: House robber - rob houses without robbing adjacent ones
     * Input: nums = [2,7,9,3,1]
     * Output: 12 (rob houses 0, 2, 4)
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * Q: Minimum coins to make amount
     * Input: coins = [1,3,4], amount = 6
     * Output: 2 (6 = 3 + 3)
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Q: Longest Common Subsequence
     * Input: text1 = "abcde", text2 = "ace"
     * Output: 3 ("ace")
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
    
    /**
     * Q: Unique Paths in grid
     * Input: m = 3, n = 7
     * Output: 28
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
    
    /**
     * Q: 0/1 Knapsack Problem
     * Input: weights = [1,3,4,5], values = [1,4,5,7], capacity = 7
     * Output: 9
     */
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int[][] dp = new int[weights.length + 1][capacity + 1];
        for (int i = 1; i <= weights.length; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[weights.length][capacity];
    }

    public static void main(String[] strings) {

        System.out.println("=== Dynamic Programming ===");
        
        System.out.println("Fibonacci(10): " + fibonacci(10));
        System.out.println("Climb stairs(5): " + climbStairs(5));
        
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("House robber: " + rob(houses));
        
        System.out.println("LCS of 'abcde' and 'ace': " + longestCommonSubsequence("abcde", "ace"));
        System.out.println("Unique paths 3x7 grid: " + uniquePaths(3, 7));
        
        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        System.out.println("Knapsack capacity 7: " + knapsack(weights, values, 7));
    }
}