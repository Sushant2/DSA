import java.io.*;
import java.util.*;

public class reverseLL {
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
            }
            temp.next = head;
            head = temp;
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

        public void addAt(int val, int idx) {
            if (idx < 0 || idx > size) {
                System.out.println("Invalid Arguments!");
                return;
            }
            if (idx == 0) {
                addFirst(val);
                return;
            }
            if (idx == size) {
                addLast(val);
            } else {
                Node temp = new Node();
                temp.data = val;
                Node prev = head;
                for (int i = 0; i < idx - 1; i++) {
                    prev = prev.next;
                }
                temp.next = prev.next;
                prev.next = temp;
                size++;
            }
        }

        public void removeFirst() {
            if (size == 0) {
                System.out.println("List is empty!");
                return;
            }
            if (size == 1) {
                head = tail = null;
            } else {
                head = head.next;
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
                Node prev = head;
                for (int i = 0; i < size - 2; i++) {
                    prev = prev.next;
                }
                prev.next = null;
                tail = prev;
            }
            size--;
        }

        public void removeAt(int idx) {
            if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments!");
                return;
            }
            if (idx == 0) {
                removeFirst();
            }
            if (idx == size - 1) {
                removeLast();
            } else {
                Node prev = head;
                for (int i = 0; i < idx - 1; i++) {
                    prev = prev.next;
                }
                prev.next = prev.next.next;
                size--;
            }
        }

        int getFirst() {
            if (size == 0) {
                System.out.println("List is empty!");
                return -1;
            }
            return head.data;
        }

        int getLast() {
            if (size == 0) {
                System.out.println("List is empty!");
                return -1;
            }
            return tail.data;
        }

        int getAt(int idx) {
            if (size == 0) {
                System.out.println("List is empty!");
                return -1;
            }
            if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments!");
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

        public void display() {
            if (size == 0) {
                System.out.println("List is empty!");
                return;
            }
            Node curr = head;
            while (curr != null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            System.out.println();
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
            }else if(str.startsWith("add"))
             else if (str.startsWith("size")) {
                list.size();
            } else if (str.startsWith("reverseDataLL")) {
                list.reverseDataLL();
            } else {
                System.out.println("Write correct input format!");
            }
            str = br.readLine();
        }
        testList(list);

    }
}