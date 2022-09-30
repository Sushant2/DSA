import java.io.*;
import java.util.*;

public class Main {

    static String[] dtoc = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        ArrayList<String> ans = getKPC(0, s);
        System.out.println(ans);
    }

    public static ArrayList<String> getKPC(int idx, String str) {
        // base
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        // faith
        ArrayList<String> smallAns = getKPC(idx + 1, str);
        ArrayList<String> ans = new ArrayList<>();

        for (Character letter : dtoc[str.charAt(idx) - '0'].toCharArray()) {
            for (String x : smallAns) {
                ans.add(letter + x);
            }
        }
        return ans;
    }

}