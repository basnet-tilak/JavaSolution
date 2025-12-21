package solution.interview.algorithms;

import java.util.*;

/**
 * Stack and Queue Algorithm Examples
 */
public class StackQueueAlgorithms {
    
    /**
     * Q: Check if parentheses in a string are valid and properly closed.
     * Input: s = "()[]{}"
     * Output: true
     */
    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put('}', '{');
        mapping.put(']', '[');
        
        for (char c : s.toCharArray()) {
            if (mapping.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != mapping.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Q: Design a stack that supports push, pop, top, and retrieving minimum element in O(1).
     * Operations: push(-2), push(0), push(-3), getMin() -> -3, pop(), top() -> 0, getMin() -> -2
     */
    static class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;
        
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }
        
        public void pop() {
            if (stack.pop().equals(minStack.peek())) {
                minStack.pop();
            }
        }
        
        public int top() {
            return stack.peek();
        }
        
        public int getMin() {
            return minStack.peek();
        }
    }
    
    /**
     * Q: Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * Input: tokens = ["2","1","+","3","*"]
     * Output: 9 (((2 + 1) * 3) = 9)
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                int b = stack.pop();
                int a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
    
    /**
     * Q: Implement a queue using two stacks.
     * Operations: push(1), push(2), peek() -> 1, pop() -> 1, empty() -> false
     */
    static class MyQueue {
        private Stack<Integer> input;
        private Stack<Integer> output;
        
        public MyQueue() {
            input = new Stack<>();
            output = new Stack<>();
        }
        
        public void push(int x) {
            input.push(x);
        }
        
        public int pop() {
            peek();
            return output.pop();
        }
        
        public int peek() {
            if (output.isEmpty()) {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
            }
            return output.peek();
        }
        
        public boolean empty() {
            return input.isEmpty() && output.isEmpty();
        }
    }
    
    /**
     * Q: Find the maximum value in each sliding window of size k.
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
     * Output: [3,3,5,5,6,7]
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
            
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Stack & Queue Algorithms ===");
        
        System.out.println("Valid parentheses '()[]{}': " + isValidParentheses("()[]{}"));
        System.out.println("Valid parentheses '([)]': " + isValidParentheses("([)]"));
        
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Min: " + minStack.getMin());
        
        String[] rpn = {"2", "1", "+", "3", "*"};
        System.out.println("RPN result: " + evalRPN(rpn));
        
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("Sliding window max: " + Arrays.toString(maxSlidingWindow(nums, 3)));
    }
}