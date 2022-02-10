import java.io.*;
import java.util.*;

public class nthnodefromend {
    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        public void addFirst(int val) {
            Node temp = new Node();
            temp.data = val;
            if (size == 0) {
                head = temp;
                tail = temp;
            } else {
                temp.next = head;
                head = temp;
            }
            size++;
        }

        public void addLast(int val) {
            Node temp = new Node();
            temp.data = val;
            if (size == 0) {
                head = temp;
                tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }
            size++;
        }

        public int size() {
            return size;
        }

        public void display() {
            for (Node temp = head; temp != null; temp = temp.next) {
                System.out.print(temp.data + " ");
            }
            System.out.println();
        }

        // ! brute force - 1. calculate size by travesing LL
        // ! 2. get nth node by getAt(totalNode-n) from starting

        public int getFirst() {
            if (size == 0) {
                System.out.println("List is empty!");
                return -1;
            }
            return head.data;
        }

        public int getLast() {
            if (size == 0) {
                System.out.println("List is empty!");
                return -1;
            }
            return tail.data;
        }

        public int getAt(int idx) {
            if (size == 0) {
                System.out.println("List is empty!");
                return -1;
            }
            if (idx < 0 || idx >= size) {
                System.out.println("Invalid Arguments!");
                return -1;
            }
            if (idx == 0)
                return getFirst();
            if (idx == size - 1)
                return getLast();
            Node curr = head;
            for (int i = 0; i < idx; i++) {
                curr = curr.next;
            }
            return curr.data;
        }

        public int nthNodeFromEndBrute(int k) {
            int count = 0;
            for (Node curr = head; curr != null; curr = curr.next)
                count++;

            return getAt(count - k);
        }

        // ! optimised approach using 2 ptrs
        public int nthNodeFromEnd(int k) {
            Node slow = head, fast = head;
            // making fast ptr n nodes ahead of slow initially
            while (fast != null && k-- > 0) {
                fast = fast.next;
            }
            // corner case
            if (k > 0)
                return -1;
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow.data;
        }
    }

    public static void testList(LinkedList list) {
        System.out.println("Final Linked List: ");
        for (Node curr = list.head; curr != null; curr = curr.next) {
            System.out.println(curr.data);
        }
        System.out.println("Size: " + list.size);
        if (list.size > 0) {
            System.out.println("Head: " + list.head.data);
            System.out.println("Tail: " + list.tail.data);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("addLast")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addLast(val);
            } else if (str.startsWith("addFirst")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addFirst(val);
            } else if (str.startsWith("display")) {
                list.display();
            } else if (str.startsWith("size")) {
                System.out.println(list.size());
            } else if (str.startsWith("nodeFromEnd")) {
                int n = Integer.parseInt(str.split(" ")[1]);
                System.out.println(list.nthNodeFromEnd(n));
            } else if (str.startsWith("nodeFromEndBrute")) {
                int n = Integer.parseInt(str.split(" ")[1]);
                System.out.println(list.nthNodeFromEndBrute(n));
            } else {
                System.out.println("Write correct input format!");
            }
            str = br.readLine();
        }
        testList(list);
    }
}
