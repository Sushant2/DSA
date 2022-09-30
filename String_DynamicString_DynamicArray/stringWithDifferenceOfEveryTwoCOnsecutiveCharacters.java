import java.io.*;
import java.util.*;

public class Main {

    public static String solution(String str) {
        String s = "";
        int diff = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            s = s + str.charAt(i);
            diff = (int) (str.charAt(i + 1) - (int) (str.charAt(i)));
            s = s + diff;
        }
        s = s + str.charAt(str.length() - 1);
        return s;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        System.out.println(solution(str));
    }

}