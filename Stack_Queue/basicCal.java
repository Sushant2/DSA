import java.io.*;
import java.util.*;

//! this basic calculator will work for "LC 224, LC 227 & lintcode 849"!
//! Handles all edge/cases with some manipulation in the input string - by refining it through our function
//? which handles spaces, unary operators n all
//! our code will also handle digits more than 1 e.g (223+3122)*12-4
//! but, except for power precedence we can do that also

public class basicCal {

    public static int performOp(int a, int b, char op) {
        int out = 0;
        switch (op) {
            case '+':
                out = a + b;
                break;
            case '-':
                out = a - b;
                break;
            case '*':
                out = a * b;
                break;
            case '/':
                out = a / b;
                break;
        }
        return out;
    }

    public static int precedence(char ch) {
        if (ch == '+' || ch == '-')
            return 1;
        if (ch == '*' || ch == '/')
            return 2;
        return 0;
    }

    public static String refine(String s) {
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // spaces handle
            if (ch == ' ')
                continue;
            // unary + handle
            else if (ch == '+') {
                if (str.length() == 0 || str.charAt(str.length() - 1) == '(')
                    str.append("0+");
                else if (str.charAt(str.length() - 1) == '+' || str.charAt(str.length() - 1) == '-')
                    continue;
                else
                    str.append('+');
            } else if (ch == '-') {
                if (str.length() == 0 || str.charAt(str.length() - 1) == '(')
                    str.append("0-");
                else if (str.charAt(str.length() - 1) == '+')
                    str.setCharAt(str.length() - 1, '-');
                else if (str.charAt(str.length() - 1) == '-')
                    str.setCharAt(str.length() - 1, '+');
                else
                    str.append('-');
            }
            // if *,/,(,)...
            else {
                str.append(ch);
            }
        }
        return str.toString();
    }

    public static void calculator(String str) {
        str = refine(str);
        // now do the same as we do in infix eevaluation
        // 2 stacks - operator & operand
        Stack<Character> operator = new Stack<>();
        Stack<Integer> operand = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(')
                operator.push(ch);
            else if (ch == ')') {
                while (operator.peek() != '(') {
                    int b = operand.pop();
                    int a = operand.pop();
                    char op = operator.pop();
                    operand.push(performOp(a, b, op));
                }
                // pop opening bracket as well
                operator.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (operator.size() > 0 && operator.peek() != '(' && precedence(operator.peek()) >= precedence(ch)) {
                    int b = operand.pop();
                    int a = operand.pop();
                    char op = operator.pop();
                    operand.push(performOp(a, b, op));
                }
                operator.push(ch);
            } else
                operand.push(ch - '0');
        }

        // at last if operator stackis not empty
        while (operator.size() > 0) {
            int b = operand.pop();
            int a = operand.pop();
            char op = operator.pop();
            operand.push(performOp(a, b, op));
        }

        System.out.println("Ans: " + operand.peek());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        calculator(exp);

    }
}
