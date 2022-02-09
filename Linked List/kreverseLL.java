import java.io.*;
import java.util.*;

public class kreverseLL {
    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        void addLast(int val) {
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

        public void removeFirst() {
            if (size == 0) {
                System.out.println("List is empty");
            } else if (size == 1) {
                head = tail = null;
                size = 0;
            } else {
                head = head.next;
                size--;
            }
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

        public void kReverse(int k) {
            LinkedList prev = new LinkedList();
            while (size > 0) { // process all nodes or process input list
                LinkedList curr = new LinkedList();
                // !corner case/edge case - if got any grp less than k
                if (size < k) {
                    while (size > 0) {
                        int val = head.data;
                        this.removeFirst();
                        curr.addLast(val);
                    }
                } else {
                    for (int i = 0; i < k; i++) {
                        int val = head.data;
                        this.removeFirst();
                        curr.addFirst(val);
                    }
                }
                // if prev is empty(initially)
                if (prev.head == null) {
                    prev = curr;
                } else {
                    prev.tail.next = curr.head;
                    prev.tail = curr.tail;
                    prev.size += curr.size;
                }
            }
            // ! at last - if prev.tail.next does not points to null at last, we'll make it
            prev.tail.next = null;
            // ! no return type, so change our prev list to input
            this.head = prev.head;
            this.tail = prev.tail;
            this.size = prev.size;

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
            if (str.startsWith("removeFirst")) {
                list.removeFirst();
            } else if (str.startsWith("addLast")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addLast(val);
            } else if (str.startsWith("size")) {
                System.out.println(list.size());
            } else if (str.startsWith("addFirst")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addFirst(val);
            } else if (str.startsWith("display")) {
                list.display();
            } else if (str.startsWith("kreverse")) {
                int k = Integer.parseInt(str.split(" ")[1]);
                list.kReverse(k);
            } else {
                System.out.println("Write correct input format!");
            }
            str = br.readLine();
        }
        testList(list);
    }
}
