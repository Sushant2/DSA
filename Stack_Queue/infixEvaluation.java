import java.io.*;
import java.util.*;

public class infixEvaluation {

    public static int precedence(char ch) {
        if (ch == '+' || ch == '-')
            return 1;
        if (ch == '*' || ch == '/')
            return 2;
        return 0;
    }

    public static int performOp(int a, char op, int b) {
        if (op == '+')
            return a + b;
        else if (op == '-')
            return a - b;
        else if (op == '*')
            return a * b;
        else
            return a / b;
    }

    public static int infixEval(String str) {
        // ? 2 stacks - 1 operand stack, 1 operator stack
        Stack<Integer> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            // ! extracting characters one by one
            char ch = str.charAt(i);
            // ? if ch is digits from 0 to 9
            if (ch >= '0' && ch <= '9') {
                operand.push(ch - '0');
            }
            // ? if ch is opening bracket
            else if (ch == '(') {
                operator.push(ch);
            }
            // ? if ch is closing bracket
            else if (ch == ')') {
                while (operator.peek() != '(') {
                    int b = operand.pop();
                    int a = operand.pop();
                    char op = operator.pop();
                    operand.push(performOp(a, op, b));
                }
                operator.pop();
            }
            // ? also have to pop opening bracket
            // ? ch is +,-,*,/
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                // ? do work till stack not empty or stack top is not opening or the precedence
                // of stk.top is greater then or equal to precedence of ch
                while (operator.size() > 0 && operator.peek() != '(' && precedence(operator.peek()) >= precedence(ch)) {
                    int b = operand.pop();
                    int a = operand.pop();
                    char op = operator.pop();
                    operand.push(performOp(a, op, b));
                }
                operator.push(ch);
            }
        }
        while (operator.size() > 0) {
            int b = operand.pop();
            int a = operand.pop();
            char op = operator.pop();
            operand.push(performOp(a, op, b));
        }
        return operand.peek();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        System.out.println("Result of infix evaluation: " + infixEval(exp));
    }
}