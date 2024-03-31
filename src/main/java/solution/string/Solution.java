package solution.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        // Write your code here.
        StringTokenizer st = new StringTokenizer(s);
        System.out.println(st.countTokens());

        while(st.hasMoreElements()){
            System.out.println(st.nextElement());
        }
        scan.close();
    }
}