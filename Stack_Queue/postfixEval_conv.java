import java.io.*;
import java.util.*;

//! evaluating postfix exp & converting postfix to prefix exp

public class postfixEval_conv {

    // ! find precedence
    public static int precedence(char op) {
        if (op == '-' || op == '+')
            return 1;
        if (op == '*' || op == '*')
            return 2;
        return 0;
    }

    // ! perform eval
    public static int performEval(int a, int b, char op) {
        if (op == '+')
            return a + b;
        else if (op == '-')
            return a - b;
        else if (op == '*')
            return a * b;
        else
            return a / b;
    }

    // ! perform postfix
    public static void performPost(Stack<String> postfix, char op) {
        String postfixB = postfix.pop();
        String postfixA = postfix.pop();
        postfix.push(op + postfixA + postfixB);
    }

    public static void evalnconv(String str) {
        // ? 2stacks - evaluation & postfix
        Stack<Integer> eval = new Stack<>();
        Stack<String> postfix = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int b = eval.pop();
                int a = eval.pop();
                eval.push(performEval(a, b, ch));
                performPost(postfix, ch);
            } else {
                eval.push(ch - '0');
                postfix.push(ch + "");
            }
        }
        System.out.println("Postfix evaluation: " + eval.peek());
        System.out.println("Postfix to prefix conversion: " + postfix.peek());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        evalnconv(exp);
    }
}
