import java.io.*;
import java.util.*;

public class middleOfLL {
    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        public void addLast(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = null;

            if (size == 0) {
                head = tail = temp;
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

        public void addFirst(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = head;
            head = temp;

            if (size == 0) {
                tail = temp;
            }
            size++;
        }

        // ! Brute force appraoch - 2traversal(not a good practice)
        // ?1. calculate size of LL(if not given in online platforms
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

        public int midLLBrute() {
            int count = 0;
            for (Node curr = head; curr != null; curr = curr.next) {
                count++;
            }
            System.out.println("Size using count: " + count);
            return getAt(count / 2);
        }

        // ! Better/Optimised approach(2ptrs/hare&tortoise/slow&fast) - O(n)
        public int midOfList() {
            Node slow = head, fast = head;
            // check for odd/even
            // even //odd
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
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
            } else if (str.startsWith("size")) {
                System.out.println(list.size());
            } else if (str.startsWith("addFirst")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addFirst(val);
            } else if (str.startsWith("display")) {
                list.display();
            } else if (str.startsWith("midOfList")) {
                System.out.println(list.midOfList());
            } else if (str.startsWith("midLLBrute")) {
                System.out.println(list.midLLBrute());
            } else {
                System.out.println("Write correct input format!");
            }
            str = br.readLine();
        }
        testList(list);
    }
}