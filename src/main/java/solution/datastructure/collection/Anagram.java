package solution.datastructure.collection;

import java.util.*;

public class Anagram {
    static boolean isAnagram(String a, String b) {
        char[] aChar =  a.toLowerCase().toCharArray();
        char[] bChar =  b.toLowerCase().toCharArray();
        Arrays.sort(aChar);
        Arrays.sort(bChar);
        if(aChar.length != bChar.length){
            return false;
        }else{
            for(int i=0; i<aChar.length; ++i){
                if(aChar[i] != bChar[i]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println( "Enter the Word: ");
        Scanner scan = new Scanner(System.in);
        System.out.println( "Enter the First  Word: ");
        String a = scan.next();
        System.out.println( "Enter the Second Word: ");
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}


