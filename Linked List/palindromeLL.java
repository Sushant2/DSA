import java.io.*;
import java.util.*;

public class palindromeLL {

    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        public int size() {
            return size;
        }

        public void display() {
            for (Node curr = head; curr != null; curr = curr.next) {
                System.out.print(curr.data + " ");
            }
            System.out.println();
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

        public void addAt(int idx, int val) {
            if (idx < 0 || idx > size) {
                System.out.println("Invalid arguments!!!");
                return;
            }
            if (idx == 0)
                addFirst(val);
            else if (idx == size)
                addLast(val);
            else {
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
            if (size == 0) {
                System.out.println("List is empty!");
                return;
            }
            if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments!!!");
                return;
            }
            if (idx == 0)
                removeFirst();
            else if (idx == size - 1)
                removeLast();
            else {
                Node prev = head;
                for (int i = 0; i < idx - 1; i++) {
                    prev = prev.next;
                }
                prev.next = prev.next.next;
                size--;
            }
        }

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
            if (idx < 0 || idx > size) {
                System.out.println("Invalid Arguments!!!");
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

        public void swap(Node left, Node right) {
            int leftData = left.data;
            left.data = right.data;
            right.data = leftData;
        }

        private Node getNodeAt(int idx) {
            Node temp = head;
            for (int i = 0; i < idx; i++) {
                temp = temp.next;
            }
            return temp;
        }

        public void reverseDI() {
            int left = 0, right = size - 1;
            while (left < right) {
                Node leftN = getNodeAt(left);
                Node rightN = getNodeAt(right);
                swap(leftN, rightN);
                left++;
                right++;
            }
        }

        public void reversePI() {
            Node prev = null, curr = head;
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

        public Node reverse(Node head) {
            Node prev = null, curr = head;
            while (curr != null) {
                Node ahead = curr.next;
                curr.next = prev;
                prev = curr;
                curr = ahead;
            }
            return prev;
        }

        public Node midLL() {
            Node slow = head, fast = head;
            Node prev = null;
            // even || odd
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            if (fast == null)
                return prev;
            return slow;
        }

        public boolean isPalindrome() {
            Node mid = midLL();
            Node second = reverse(mid.next);
            while (head != null && second != null) {
                if (head.data != second.data)
                    return false;
                head = head.next;
                second = second.next;
            }
            return true;
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
            } else if (str.startsWith("isPalindrome")) {
                if (list.isPalindrome()) {
                    System.out.println("List is palindrome!");
                } else {
                    System.out.println("List is not palindrome!");
                }
            } else {
                System.out.println("Write correct input format!");
            }
            str = br.readLine();
        }
        testList(list);
    }
}
