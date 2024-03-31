package solution.datastructure.collection.list_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayList_ {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.forEach(System.out::println);
    }
}
