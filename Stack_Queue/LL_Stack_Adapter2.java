import java.io.*;
import java.util.*;

public class LL_Stack_Adapter2 {
    public static class StackUsingLL {
        private class Node {
            int data;
            Node next;
        }

        Node head;

        StackUsingLL() {
            this.head = null;
        }

        public void push(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = head;
            head = temp;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public int peek() {
            if (!isEmpty()) {
                return head.data;
            } else {
                System.out.println("Stack underflow!");
                return -1;
            }
        }

        public void pop() {
            if (head == null) {
                System.out.println("Stack underflow!");
                return;
            }
            head = head.next;
        }

        public void display() {
            if (head == null) {
                System.out.println("Stack Underflow!");
                // exit(1);
            } else {
                for (Node temp = head; temp != null; temp = temp.next) {
                    System.out.print(temp.data + " ");
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        StackUsingLL stk = new StackUsingLL();
        stk.push(10);
        stk.push(20);
        stk.push(30);
        stk.push(40);
        stk.display();
        System.out.println("Top of stack: " + stk.peek());
        stk.pop();
        stk.pop();
        stk.pop();
        stk.display();
        stk.pop();
        stk.peek();
    }
}
