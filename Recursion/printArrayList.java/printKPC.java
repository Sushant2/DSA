import java.io.*;
import java.util.*;

public class Main {

    static String[] dtoc = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String input = scn.next();
        printKPC(0, input, "");
    }

    public static void printKPC(int idx, String input, String output) {
        // base
        if (idx == input.length()) {
            System.out.println(output);
            return;
        }
        // exploring all options at every index
        for (Character letter : dtoc[input.charAt(idx) - '0'].toCharArray()) {
            // faith
            printKPC(idx + 1, input, output + letter);
        }
    }

}