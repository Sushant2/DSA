import java.io.*;
import java.util.*;

public class duplicate_brackets {
    public static boolean checkRedundant(String str) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ')') {
                if (stk.peek() == '(')
                    return true;
                while (stk.peek() != '(') {
                    stk.pop();
                }
                stk.pop();
            } else
                stk.push(ch);
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("areDuplicates")) {
                String exp = str.split(" ")[1];
                if (checkRedundant(exp) == true)
                    System.out.println("String is redundant!");
                else
                    System.out.println("String is not redundant!");
            } else {
                System.out.println("Write correct command!");
            }
            str = br.readLine();
        }
    }
}
