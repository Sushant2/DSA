import java.io.*;
// import java.util.*;

public class reverseNodesLL {
    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        public void size() {
            System.out.println("Curr size: " + size);
        }

        public void addFirst(int val) {
            Node temp = new Node();
            temp.data = val;
            if (size == 0) {
                head = tail = temp;
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
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }
            size++;
        }

        public Node getFirst() {
            if (size == 0) {
                System.out.println("List is empty");
            }
            return head;
        }

        public Node getLast() {
            if (size == 0) {
                System.out.println("List is empty!");
            }
            return tail;
        }

        public Node getAt(int idx) {
            if (size == 0) {
                System.out.println("List is empty!");
            }
            if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments!");
            }
            if (idx == 0)
                getFirst();
            if (idx == size - 1)
                getLast();
            Node curr = head;
            for (int i = 0; i < idx; i++) {
                curr = curr.next;
            }
            return curr;
        }

        public void swap(Node first, Node second) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }

        public void reverseNodesLL() {
            Node prev = null;
            Node curr = head;
            while (curr != null) {
                Node ahead = curr.next;
                curr.next = prev;
                prev = curr;
                curr = ahead;
            }
            Node temp = head;
            head = tail;
            tail = temp;
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
            } else if (str.startsWith("size")) {
                list.size();
            } else if (str.startsWith("reverseNodesLL")) {
                list.reverseNodesLL();
            } else {
                System.out.println("Write correct input format!");
            }
            str = br.readLine();
        }
        testList(list);
    }
}
