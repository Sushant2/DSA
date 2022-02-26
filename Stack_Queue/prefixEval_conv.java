import java.util.*;

import javax.crypto.interfaces.PBEKey;

import java.io.*;

public class prefixEval_conv {

    public static int preformEval(int a, int b, char ch) {
        if (ch == '+')
            return a + b;
        else if (ch == '-')
            return a - b;
        else if (ch == '*')
            return a * b;
        else
            return a / b;
    }

    public static void performPre(Stack<String> postfix, char op) {
        String postfixA = postfix.pop();
        String postfixB = postfix.pop();
        postfix.push(postfixA + postfixB + op);
    }

    public static void prefixEvalConv(String str) {
        Stack<String> postfix = new Stack<>();
        Stack<Integer> eval = new Stack<>();
        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int a = eval.pop();
                int b = eval.pop();
                eval.push(preformEval(a, b, ch));
                performPre(postfix, ch);
            } else {
                eval.push(ch - '0');
                postfix.push(ch + "");
            }
        }

        System.out.println("Prefix evaluation: " + eval.peek());
        System.out.println("Prefix to postfix conversion: " + postfix.peek());
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        prefixEvalConv(exp);
    }
}
