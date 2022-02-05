//! PUSH EFFICIENT - 

import java.io.*;
import java.util.*;

public class Stack_Queue_Adapter {
    public static class queueUsingStack {
        Stack<Integer> mainS;
        Stack<Integer> helperS;

        public queueUsingStack() {
            mainS = new Stack<>();
            helperS = new Stack<>();
        }

        int size() {
            return mainS.size();
        }

        void add(int val) {
            mainS.push(val);
        }

        int remove() {
            if (size() == 0) {
                System.out.println("Queue underflow!");
                return -1;
            }
            while (mainS.size() > 0) {
                helperS.push(mainS.pop());
            }
            int val = helperS.pop();
            while (helperS.size() > 0) {
                mainS.push(helperS.pop());
            }
            return val;
        }

        int peek() {
            if (size() == 0) {
                System.out.println("Queue underflow!");
                return -1;
            }
            while (mainS.size() > 0) {
                helperS.push(mainS.pop());
            }
            int val = helperS.peek();
            while (helperS.size() > 0) {
                mainS.push(helperS.pop());
            }
            return val;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        queueUsingStack qu = new queueUsingStack();

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("add")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                qu.add(val);
            } else if (str.startsWith("remove")) {
                int val = qu.remove();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("peek")) {
                int val = qu.peek();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("size")) {
                System.out.println(qu.size());
            } else {
                System.out.println("Enter valid command!");
            }
            str = br.readLine();
        }
    }
}
