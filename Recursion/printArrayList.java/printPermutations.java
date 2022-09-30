import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String input = scn.next();
        printPermutations(input, "");
    }

    public static void printPermutations(String input, String output) {
        // base
        if (input.length() == 0) {
            System.out.println(output);
            return;
        }

        // explore each character at each level
        // indexing based loop for deleting thaat choosen character & modifying input
        // string
        for (int i = 0; i < input.length(); i++) {
            // new string
            String newString = input.substring(0, i) + input.substring(i + 1);
            // faith
            printPermutations(newString, output + input.charAt(i));
        }
    }

}