import java.io.*;
// import java.util.*;

public class removeLast {
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
            if (size == 0) {
                head = temp;
                tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }
            size++;
        }

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

        public void removeFirst() {
            if (size == 0) {
                System.out.println("List is empty!");
                return;
            }
            if (size > 1) {
                head = head.next;
            } else {
                head = tail = null;
            }
            size--;
        }

        public void removeLast() {
            if (size == 0) {
                System.out.println("List is empty!");
                return;
            }
            if (size == 1) {
                head = tail = null;
            } else {
                Node prevTail = head;
                for (int i = 0; i < size - 2; i++) {
                    prevTail = prevTail.next;
                }
                prevTail.next = null;
                tail = prevTail;
            }
            size--;
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
            } else if (str.startsWith("removeFirst")) {
                list.removeFirst();
            } else if (str.startsWith("addFirst")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addFirst(val);
            } else if (str.startsWith("removeLast")) {
                list.removeLast();
            } else {
                System.out.println("Write correct input format!");
            }
            str = br.readLine();
        }
        testList(list);
    }
}