package solution.datastructure.collection.set_;

import java.util.Vector;

public class Vector_ {
        public static void main(String[] args) {
            // Creating a Vector of strings
            Vector<String> vector = new Vector<>();

            // Adding elements to the Vector
            vector.add("Apple");
            vector.add("Banana");
            vector.add("Orange");

            // Accessing elements by index
            System.out.println("Element at index 1: " + vector.elementAt(1));

            // Removing an element
            vector.removeElement("Banana");

            // Iterating over elements
            System.out.println("Elements in the Vector:");
            for (String fruit : vector) {
                System.out.println(fruit);
            }
        }
    }
