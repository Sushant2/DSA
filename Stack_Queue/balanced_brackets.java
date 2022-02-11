import java.io.*;
import java.util.*;

public class balanced_brackets {

    public static boolean isBalanced(String str) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ')') {
                if (stk.isEmpty() || stk.peek() != '(')
                    return false;
                stk.pop();
            } else if (ch == '}') {
                if (stk.isEmpty() || stk.peek() != '{')
                    return false;
                stk.pop();
            } else if (ch == ']') {
                if (stk.isEmpty() || stk.peek() != '[')
                    return false;
                stk.pop();
            } else if (ch == '(' || ch == '{' || ch == '[')
                stk.push(ch);
            else
                continue;
        }
        return (stk.size() > 0) ? false : true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder str = new StringBuilder();
        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("isBalanced")) {
                // String exp = br.readLine();
                String exp = str.split(" ")[1];
                if (isBalanced(exp) == true) {
                    System.out.println("String is balanced!");
                } else {
                    System.out.println("String is not balanced!");
                }
            } else {
                System.out.println("Write correct command!");
            }
            str = br.readLine();
        }
    }
}