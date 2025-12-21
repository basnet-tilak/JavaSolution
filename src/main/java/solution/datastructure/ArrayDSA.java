package solution.datastructure;

import java.util.Arrays;

public class ArrayDSA {
    public static void main(String[] args) {
        int [] numbers = { 10, 20, 30, 40, 50, 150};
        String[] str = {"a", "b", "c", "d"};
        Arrays.stream(str).forEach(System.out::println);
        Arrays.stream(numbers).sorted().forEach(System.out::println);
    }
}
