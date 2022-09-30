import java.io.*;
import java.util.*;

public class Main {

    public static String compression1(String str) {
        String s = "";
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || str.charAt(i) != str.charAt(i - 1))
                s = s + str.charAt(i);

        }
        return s;
    }

    public static String compression2(String str) {
        int freq = 0;
        String s = "";
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || str.charAt(i) != str.charAt(i - 1)) {
                if (freq > 1) {
                    s = s + freq;
                }
                s = s + str.charAt(i);
                freq = 0;
            }
            freq++;
        }
        if (freq > 1)
            s = s + freq;

        return s;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        System.out.println(compression1(str));
        System.out.println(compression2(str));
    }

}