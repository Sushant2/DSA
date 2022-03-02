import java.util.*;
import java.io.*;

public class smallestNumFollowPattern {

    public static void smallestNumberFollowingPattern(String str) {
        Stack<Integer> stk = new Stack<>();
        int num = 1;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'd') {
                stk.push(num);
                num++;
            } else {
                stk.push(num);
                num++;
                while (stk.size() != 0) {
                    System.out.print(stk.pop());
                }
            }
        }
        // at last pust remaining
        stk.push(num);
        while (stk.size() > 0) {
            System.out.print(stk.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        smallestNumberFollowingPattern(str);
    }
}
