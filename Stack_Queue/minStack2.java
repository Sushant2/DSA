import java.util.*;
import java.io.*;

//! using encryption and decryption - with constant space
public class minStack2 {
    public static class stack {
        Stack<Integer> stk = new Stack<>();
        int min;

        public void push(int val) {
            if (stk.size() == 0) {
                stk.push(val);
                min = val;
            } else if (val >= min)
                stk.push(val);
            else {
                stk.push(val + val - min);
                min = val;
            }
        }

        public int pop() {
            if (size() == 0)
                return -1;
            if (stk.peek() >= min)
                return stk.pop();
            else {
                int val = min;
                min = 2 * min - stk.pop();
                return val;
            }
        }

        public int top() {
            if (size() == 0)
                return -1;
            else if (stk.peek() >= min)
                return stk.peek();
            else
                return min;
        }

        public int size() {
            return stk.size();
        }

        public int min() {
            if (size() == 0)
                return -1;
            return min;
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
