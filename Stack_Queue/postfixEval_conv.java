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
    public static void performPost(Stack<String> prefix, char op) {
        String prefixB = prefix.pop();
        String prefixA = prefix.pop();
        prefix.push(op + prefixA + prefixB);
    }

    public static void performInfix(Stack<String> infix, char op) {
        String infixB = infix.pop();
        String infixA = infix.pop();
        infix.push("(" + infixA + op + infixB + ")");
    }

    public static void evalnconv(String str) {
        // ? 3stacks - evaluation, postfix * infix
        Stack<Integer> eval = new Stack<>();
        Stack<String> prefix = new Stack<>();
        Stack<String> infix = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int b = eval.pop();
                int a = eval.pop();
                eval.push(performEval(a, b, ch));
                performPost(prefix, ch);
                performInfix(infix, ch);
            } else {
                eval.push(ch - '0');
                prefix.push(ch + "");
                infix.push(ch + "");
            }
        }
        System.out.println("Postfix evaluation: " + eval.peek());
        System.out.println("Postfix to prefix conversion: " + prefix.peek());
        System.out.println("Postfix to infix conversion: " + infix.peek());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        evalnconv(exp);
    }
}
