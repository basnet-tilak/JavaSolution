package bsnt.np.datastructure;

import java.util.Arrays;

public class ArrayDSA {
    public static void main(String[] args) {
        int [] numbers = { 10, 20, 30, 40, 50, 150};
        char[] ch = {'a', 'c', 'f' ,'a'};

        Arrays.sort(ch);

        Arrays.stream(numbers).sorted();
        
        int length = numbers.length;
        System.out.println(length);
        System.out.println(java.util.Arrays.stream(numbers).max().getAsInt());
        System.out.println(java.util.Arrays.stream(numbers).min().getAsInt());
        java.util.Arrays.stream(numbers).forEach(System.out::println);
    }
}
