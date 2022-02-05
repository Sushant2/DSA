import java.io.*;
import java.util.*;

public class Queue_Stack_Adapter {
    public static class stackUsingQueue {
        Queue<Integer> q;

        public stackUsingQueue() {
            q = new ArrayDeque<>();
        }

        int size() {
            return q.size();
        }

        void push(int val) {
            q.add(val);
        }

        int pop() {
            if (size() == 0) {
                System.out.println("Stack underflow!");
                return -1;
            }
            int val = 0;
            for (int i = 0; i < size(); i++) {
                val = q.remove();
                if (i < size()) {
                    q.add(val);
                }
            }
            return val;
        }

        int top() {
            if (size() == 0) {
                System.out.println("Stack underflow!");
                return -1;
            }
            int val = 0;
            for (int i = 0; i < size(); i++) {
                val = q.remove();
                q.add(val);
            }
            return val;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        stackUsingQueue st = new stackUsingQueue();

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
            } else {
                System.out.println("Enter valid command!");
            }
            str = br.readLine();
        }
    }
}
