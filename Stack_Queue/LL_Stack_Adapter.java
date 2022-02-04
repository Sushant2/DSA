import java.io.*;
import java.util.*;

public class LL_Stack_Adapter {
    // ! w/o using java collections
    // public static class Node {
    // int data;
    // Node next;
    // }

    // public static class LinkedList {
    // Node head;
    // Node tail;
    // int size;

    // int size() {
    // System.out.println("Curr Linked List Size: " + size);
    // return size;
    // }

    // public void addFirst(int val) {
    // Node temp = new Node();
    // temp.data = val;
    // if (size == 0) {
    // head = tail = temp;
    // } else {
    // temp.next = head;
    // temp = head;
    // }
    // size++;
    // }

    // public int removeFirst() {
    // if (size == 0) {
    // System.out.println("List is empty!");
    // return -1;
    // }
    // int val = 0;
    // if (size == 1) {
    // val = head.data;
    // head = tail = null;
    // } else {
    // val = head.data;
    // head = head.next;
    // }
    // size--;
    // return val;
    // }

    // public int getFirst() {
    // if (size == 0) {
    // System.out.println("List is empty!");
    // return -1;
    // }
    // return head.data;
    // }

    // public void displayLL() {
    // if (size() == 0) {
    // System.out.println("LL is empty!");
    // return;
    // }
    // for (Node temp = head; temp != null; temp = temp.next) {
    // System.out.print(temp.data + " ");
    // }
    // System.out.println();
    // }
    // }

    // LinkedList list;

    // public LL_Stack_Adapter() { // constructor
    // list = new LinkedList();
    // }

    // int size() {
    // return list.size();
    // }

    // void push(int val) {
    // list.addFirst(val);
    // }

    // int pop() {
    // if (size() == 0) {
    // System.out.println("Stack Underflow!");
    // return -1;
    // }
    // return list.removeFirst();
    // }

    // int top() {
    // if (size() == 0) {
    // System.out.println("Stack Underflow!");
    // return -1;
    // }
    // return list.getFirst();
    // }

    // void displayLL() {
    // list.displayLL();
    // }

    //! with Java collections
    public static class LLToStackAdapter {
        LinkedList<Integer> list;

        public LLToStackAdapter() {
            list = new LinkedList<>();
        }

        int size() {
            return list.size();
        }

        void push(int val) {
            list.addFirst(val);
        }

        int pop() {
            if (size() == 0) {
                System.out.println("Stack underflow!");
                return -1;
            }
            return list.removeFirst();
        }

        int top() {
            if (size() == 0) {
                System.out.println("Stack underflow!");
                return -1;
            }
            return list.getFirst();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LLToStackAdapter st = new LLToStackAdapter();

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