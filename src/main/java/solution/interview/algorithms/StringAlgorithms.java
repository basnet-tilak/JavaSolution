package solution.interview.algorithms;

import java.util.*;

/**
 * String Algorithm Interview Questions
 */
public class StringAlgorithms {
    
    /**
     * Q: Reverse String using StringBuilder
     * Input: str = "hello"
     * Output: "olleh"
     */
    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    /**
     * Q: Check if string is palindrome
     * Input: str = "A man, a plan, a canal: Panama"
     * Output: true
     */
    public static boolean isPalindrome(String str) {
        String cleaned = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }
    
    /**
     * Q: Check if two strings are anagrams
     * Input: str1 = "listen", str2 = "silent"
     * Output: true
     */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        char[] chars1 = str1.toLowerCase().toCharArray();
        char[] chars2 = str2.toLowerCase().toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }
    
    /**
     * Q: Longest Substring Without Repeating Characters
     * Input: s = "abcabcbb"
     * Output: 3 (substring "abc")
     */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0, maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
    
    /**
     * Q: Group Anagrams
     * Input: strs = ["eat","tea","tan","ate","nat","bat"]
     * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
    
    /**
     * Q: Longest Common Prefix
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
    
    /**
     * Q: First Non-Repeating Character
     * Input: s = "leetcode"
     * Output: 0 (index of 'l')
     */
    public static int firstUniqChar(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (count.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }
    
    /**
     * Q: String to Integer (atoi)
     * Input: s = "   -42"
     * Output: -42
     */
    public static int myAtoi(String s) {
        int i = 0, sign = 1, result = 0;
        while (i < s.length() && s.charAt(i) == ' ') i++;
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i++) == '-' ? -1 : 1;
        }
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i++) - '0';
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
        }
        return result * sign;
    }
    
    public static void main(String[] args) {
        System.out.println("=== String Algorithms ===");
        
        System.out.println("Reverse 'hello': " + reverseString("hello"));
        System.out.println("'racecar' is palindrome: " + isPalindrome("racecar"));
        System.out.println("'listen' and 'silent' are anagrams: " + areAnagrams("listen", "silent"));
        System.out.println("Longest substring in 'abcabcbb': " + lengthOfLongestSubstring("abcabcbb"));
        
        String[] anagrams = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Group Anagrams: " + groupAnagrams(anagrams));
        
        String[] prefixes = {"flower", "flow", "flight"};
        System.out.println("Longest Common Prefix: " + longestCommonPrefix(prefixes));
        
        System.out.println("First unique char in 'leetcode': " + firstUniqChar("leetcode"));
        System.out.println("String to int '   -42': " + myAtoi("   -42"));
    }
}