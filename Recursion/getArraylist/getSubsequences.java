import java.io.*;
import java.util.*;

public class getSubsequences {
    public static ArrayList<String> getSS(int idx, String str) {
        // base
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add(" ");
            return base;
        }
        // faith
        ArrayList<String> smallSubstr = getSS(idx + 1, str);
        // meeting expec of YES & NO in adding ele in result subset
        // final arraylist of subsets that is "ans"
        ArrayList<String> ans = new ArrayList<>();
        // NO
        for (String substr : smallSubstr) {
            ans.add(substr);
        }
        // YES
        for (String substr : smallSubstr) {
            ans.add(str.charAt(idx) + substr);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        ArrayList<String> substr = getSS(0, str);
        System.out.println(substr);
    }
}
