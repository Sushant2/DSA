import java.util.*;
import java.io.*;

public class minStack {
    public static class stack {
        Stack<Integer> actualStk;
        Stack<Integer> minStk;

        public stack() {
            actualStk = new Stack<>();
            minStk = new Stack<>();
            minStk.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            actualStk.push(val);
            minStk.push(Math.min(val, minStk.peek()));
        }

        public int pop() {
            if (size() == 0)
                return -1;
            minStk.pop();
            return actualStk.pop();
        }

        public int top() {
            if (size() == 0)
                return -1;
            return actualStk.peek();
        }

        public int size() {
            return actualStk.size();
        }

        public int min() {
            return minStk.peek();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        stack st = new stack();
        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("push")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                st.push(val);
            } else if (str.startsWith("pop")) {
                int val = st.pop();
                if (val != -1)
                    System.out.println(val);
            } else if (str.startsWith("top")) {
                int val = st.top();
                if (val != -1)
                    System.out.println(val);
            } else if (str.startsWith("size")) {
                System.out.println(st.size());
            } else if (str.startsWith("min")) {
                int val = st.min();
                if (val != -1)
                    System.out.println(val);
            }
            str = br.readLine();
        }
    }
}
