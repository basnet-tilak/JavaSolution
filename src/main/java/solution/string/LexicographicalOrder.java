package solution.string;

import java.util.ArrayList;
import java.util.List;

public class LexicographicalOrder {
    public static void main(String[] args) {
        System.out.println(getSmallestAndLargest("Tilakwxyz", 3));
    }
    public static List<String> getSmallestAndLargest(String a, int k){
        List<String> subStringList = new ArrayList<>();
        int i = 0;
        for(char c : a.toCharArray()){
            subStringList.add(String.valueOf(c));
        }
        return subStringList;
    }
}
