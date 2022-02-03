import java.io.*;
import java.util.*;

public class Dynamic_Stack_AL {
    public static class customStack {
        int[] data;
        int top;

        public customStack(int cap) {
            data = new int[cap];
            top = -1;
        }

        void push(int val) {
            if (top == data.length - 1) {
                int[] temp = new int[2 * data.length];
                for (int i = 0; i < data.length; i++) {
                    temp[i] = data[i];
                }
                data = temp; // copying reference
            }
            top++;
            data[top] = val;
        }

        int pop() {
            if (top == -1) {
                System.out.println("Stack Underflow!");
                return -1;
            }
            int val = data[top];
            top--;
            return val;
        }

        int top() {
            if (top == -1) {
                System.out.println("Stack Underflow!");
                return -1;
            }
            int val = data[top];
            return val;
        }

        int size() {
            return top + 1;
        }

        void display() {
            for (int i = top; i >= 0; i--) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        customStack st = new customStack(n);

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("push")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                st.push(val);
            } else if (str.startsWith("pop")) {
                int val = st.pop();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("top")) {
                int val = st.top();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("size")) {
                System.out.println(st.size());
            } else if (str.startsWith("display")) {
                st.display();
            }else{
                System.out.println("Enter valid command!");
            }
            str = br.readLine();
        }
    }
}
