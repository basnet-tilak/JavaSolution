package solution.datastructure.collection.set_;

import java.util.*;

/**
 *In Java, NavigableSet is an interface in the Java Collections Framework
 * that extends the functionality of the SortedSet interface.
 * It provides navigation methods to traverse the set in either ascending or descending order,
 * as well as methods for finding elements relative to a given element,
 * such as finding the closest element that is greater than or equal to a specified value.
 */
public class NavigableHashSet {
    public static void main(String[] args) {
        NavigableSet<Integer> numbers = new TreeSet<>();

        // Adding elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        // Finding elements
        System.out.println("Ceiling: " + numbers.ceiling(25));
        System.out.println("Floor: " + numbers.floor(25));
        System.out.println("Higher: " + numbers.higher(25));
        System.out.println("Lower: " + numbers.lower(25));

        // Retrieving and removing elements
        System.out.println("Poll first: " + numbers.pollFirst());
        System.out.println("Poll last: " + numbers.pollLast());

        // Iterating in descending order
        NavigableSet<Integer> descendingNumbers = numbers.descendingSet();
        for (Integer num : descendingNumbers) {
            System.out.println(num);
        }
    }
}
