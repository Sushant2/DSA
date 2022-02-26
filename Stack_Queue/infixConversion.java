import java.io.*;
import java.util.*;

//! converting infix expression to postfix & prefix expression simultaneously
public class infixConversion {

    public static int precedence(char ch) {
        if (ch == '+' || ch == '-')
            return 1;
        if (ch == '*' || ch == '/')
            return 2;
        return 0;
    }

    public static void performOp(Stack<String> prefix, Stack<String> postfix, char op) {
        String prefixB = prefix.pop();
        String prefixA = prefix.pop();
        prefix.push(op + prefixA + prefixB);
        String postfixB = postfix.pop();
        String postfixA = postfix.pop();
        postfix.push(postfixA + postfixB + op);
    }

    public static void infixConv(String str) {
        // ? 2 stacks - postfix & prefix of type "string"
        // ? 1 stack - operators
        Stack<String> prefix = new Stack<>();
        Stack<String> postfix = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                prefix.push(ch + "");
                postfix.push(ch + "");
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    char op = operators.pop();
                    performOp(prefix, postfix, op);
                }
                // ? pop opening bracket as well
                operators.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (operators.size() > 0 && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(ch)) {
                    char op = operators.pop();
                    performOp(prefix, postfix, op);
                }
                // ? push ourself
                operators.push(ch);
            }
        }
        while (operators.size() > 0) {
            char op = operators.pop();
            performOp(prefix, postfix, op);
        }
        System.out.println("Prefix: " + prefix.peek());
        System.out.println("Postfix: " + postfix.peek());
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        infixConv(exp);
    }
}
