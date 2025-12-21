package solution.interview.algorithms;

/**
 * LinkedList Algorithm Interview Questions
 */
public class LinkedListAlgorithms {
    
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    /**
     * Q: Reverse a singly linked list
     * Input: head = [1,2,3,4,5]
     * Output: [5,4,3,2,1]
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null, current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
    
    /**
     * Q: Merge two sorted linked lists
     * Input: list1 = [1,2,4], list2 = [1,3,4]
     * Output: [1,1,2,3,4,4]
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
    
    /**
     * Q: Detect cycle in linked list using Floyd's algorithm
     * Input: head = [3,2,0,-4], pos = 1
     * Output: true
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
    
    /**
     * Q: Remove Nth Node From End
     * Input: head = [1,2,3,4,5], n = 2
     * Output: [1,2,3,5]
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        for (int i = 0; i <= n; i++) first = first.next;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
    
    /**
     * Q: Find Middle of Linked List
     * Input: head = [1,2,3,4,5]
     * Output: [3,4,5]
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    /**
     * Q: Check if Linked List is Palindrome
     * Input: head = [1,2,2,1]
     * Output: true
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalf = reverseList(slow.next);
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }
    
    /**
     * Q: Intersection of Two Linked Lists
     * Input: listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]
     * Output: [8,4,5]
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }

    public static void main(String[] strings) {

        System.out.println("=== LinkedList Algorithms ===");
        
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode merged = mergeTwoLists(list1, list2);
        System.out.println("Merged two sorted lists");
        
        ListNode test = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode reversed = reverseList(test);
        System.out.println("Reversed linked list");
        
        ListNode removeTest = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        removeNthFromEnd(removeTest, 2);
        System.out.println("Removed 2nd node from end");
        
        ListNode middleTest = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        middleNode(middleTest);
        System.out.println("Found middle node");
        
        ListNode palindrome = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        System.out.println("Is palindrome: " + isPalindrome(palindrome));
    }
}