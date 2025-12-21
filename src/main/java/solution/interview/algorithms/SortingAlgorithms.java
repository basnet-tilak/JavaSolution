package solution.interview.algorithms;

import java.util.*;

/**
 * Sorting Algorithm Interview Questions
 */
public class SortingAlgorithms {
    
    /**
     * Q: Quick Sort Implementation
     * Input: arr = [3,6,8,10,1,2,1]
     * Output: [1,1,2,3,6,8,10]
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    /**
     * Q: Merge Sort Implementation
     * Input: arr = [38,27,43,3,9,82,10]
     * Output: [3,9,10,27,38,43,82]
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, left, temp.length);
    }
    
    /**
     * Q: Kth Largest Element in Array
     * Input: nums = [3,2,1,5,6,4], k = 2
     * Output: 5
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) heap.poll();
        }
        return heap.peek();
    }
    
    /**
     * Q: Sort Colors (Dutch National Flag)
     * Input: nums = [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     */
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low++, mid++);
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high--);
            }
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Sorting Algorithms ===");
        
        int[] quickArr = {3, 6, 8, 10, 1, 2, 1};
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(quickArr));
        
        int[] mergeArr = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(mergeArr));
        
        int[] kthArr = {3, 2, 1, 5, 6, 4};
        System.out.println("2nd largest: " + findKthLargest(kthArr, 2));
        
        int[] colors = {2, 0, 2, 1, 1, 0};
        sortColors(colors);
        System.out.println("Sort Colors: " + Arrays.toString(colors));
    }
}