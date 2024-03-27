package bsnt.np.string;

public class AllOperations {
    public static void main(String[] args) {
        stringOperations();
    }
    public static void stringOperations(){
        String str = "This is for the check the operations of the String";
        System.out.println("Length of of String: "+str.toCharArray().length);
        // convert the string as a character array
        char[] strCharArray = str.toCharArray();
        // Remove the white space of the beginning
        System.out.println(str.strip());
        // print the first character of the string
        System.out.println(strCharArray[0]);
        // Print the character index between 0 and 1
        System.out.println(str.charAt(1));

        for (char c : strCharArray) {
            System.out.print(c);
        }
    }
}
