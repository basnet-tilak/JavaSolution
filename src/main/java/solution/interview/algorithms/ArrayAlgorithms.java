package solution.interview.algorithms;

import java.util.*;

/**
 * Array Algorithm Interview Questions
 */
public class ArrayAlgorithms {
    
    /**
     * Q: Two Sum - Find indices of two numbers that add up to target
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
    
    /**
     * Q: Maximum Subarray Sum (Kadane's Algorithm)
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6 (subarray [4,-1,2,1])
     */
    public static int maxSubarraySum(int[] nums) {
        int maxSum = nums[0], currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
    
    /**
     * Q: Binary Search in sorted array
     * Input: arr = [1,3,5,7,9], target = 5
     * Output: 2
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
    
    /**
     * Q: Move Zeros to end while maintaining order
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     */
    public static void moveZeros(int[] nums) {
        int writeIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[writeIndex++] = nums[i];
            }
        }
        while (writeIndex < nums.length) {
            nums[writeIndex++] = 0;
        }
    }
    
    /**
     * Q: Three Sum - Find triplets that sum to zero
     * Input: nums = [-1,0,1,2,-1,-4]
     * Output: [[-1,-1,2],[-1,0,1]]
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++; right--;
                } else if (sum < 0) left++;
                else right--;
            }
        }
        return result;
    }
    
    /**
     * Q: Container With Most Water
     * Input: height = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     */
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1, maxArea = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return maxArea;
    }
    
    /**
     * Q: Product of Array Except Self
     * Input: nums = [1,2,3,4]
     * Output: [24,12,8,6]
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }
        return result;
    }
    
    /**
     * Q: Rotate Array
     * Input: nums = [1,2,3,4,5,6,7], k = 3
     * Output: [5,6,7,1,2,3,4]
     */
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++; end--;
        }
    }

   public static void main(String[] strings) {


        System.out.println("=== Array Algorithms ===");
        
        int[] nums = {2, 7, 11, 15};
        System.out.println("Two Sum: " + Arrays.toString(twoSum(nums, 9)));
        
        int[] maxSub = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Max Subarray Sum: " + maxSubarraySum(maxSub));
        
        int[] sorted = {1, 3, 5, 7, 9};
        System.out.println("Binary Search for 5: " + binarySearch(sorted, 5));
        
        int[] threeNums = {-1, 0, 1, 2, -1, -4};
        System.out.println("Three Sum: " + threeSum(threeNums));
        
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Max Water Area: " + maxArea(heights));
        
        int[] products = {1, 2, 3, 4};
        System.out.println("Product Except Self: " + Arrays.toString(productExceptSelf(products)));
        
        int[] rotateNums = {1, 2, 3, 4, 5, 6, 7};
        rotate(rotateNums, 3);
        System.out.println("Rotated Array: " + Arrays.toString(rotateNums));
    }
}