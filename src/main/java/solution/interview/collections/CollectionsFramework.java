package solution.interview.collections;

import java.util.*;

/**
 * Collections Framework Examples
 */
public class CollectionsFramework {
    
    public static void listComparison() {
        System.out.println("=== List Comparison ===");
        
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println("ArrayList: " + arrayList);
        System.out.println("Get element at index 1: " + arrayList.get(1));
        
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.addFirst(0);
        System.out.println("LinkedList: " + linkedList);
    }
    
    public static void hashMapDemo() {
        System.out.println("\n=== HashMap Operations ===");
        
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 5);
        map.put("banana", 3);
        map.put("orange", 8);
        
        System.out.println("Apple count: " + map.get("apple"));
        System.out.println("Contains 'banana': " + map.containsKey("banana"));
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        map.computeIfAbsent("pear", k -> 10);
        System.out.println("After computeIfAbsent: " + map);
    }
    
    public static void setOperations() {
        System.out.println("\n=== Set Operations ===");
        
        Set<Integer> hashSet = new HashSet<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));
        System.out.println("HashSet (no order): " + hashSet);
        
        Set<Integer> treeSet = new TreeSet<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));
        System.out.println("TreeSet (sorted): " + treeSet);
        
        Set<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);
    }
    
    public static void queueDemo() {
        System.out.println("\n=== Queue Operations ===");
        
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        
        System.out.println("Queue: " + queue);
        System.out.println("Poll: " + queue.poll());
        System.out.println("After poll: " + queue);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(30);
        pq.offer(10);
        pq.offer(20);
        
        System.out.println("PriorityQueue polling:");
        while (!pq.isEmpty()) {
            System.out.println("Poll: " + pq.poll());
        }
    }
    
    public static void main(String[] args) {
        listComparison();
        hashMapDemo();
        setOperations();
        queueDemo();
    }
}