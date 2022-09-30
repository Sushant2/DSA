import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String input = scn.next();
        printSS(0, input, "");
    }

    public static void printSS(int idx, String input, String ans) {
        if (idx == input.length()) {
            System.out.println(ans);
            return;
        }

        char ch = input.charAt(idx);
        // faith
        // yes
        printSS(idx + 1, input, ans + ch);
        // no
        printSS(idx + 1, input, ans);

    }

}