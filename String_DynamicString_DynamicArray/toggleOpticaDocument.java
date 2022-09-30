import java.io.*;
import java.util.*;

public class Main {

    public static String solution(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                res += (char) (str.charAt(i) + 32);
            } else {
                res += (char) (str.charAt(i) - 32);
            }
        }
        return res;

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        String res = solution(str);
        System.out.println(res);
    }

}