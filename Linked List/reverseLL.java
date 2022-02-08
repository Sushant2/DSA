import java.io.*;
import java.util.*;

public class ReverseLL {
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

        // ! Reversing Linked list - Data iterative
        public void swap(Node left, Node right) {
            int leftData = left.data;
            left.data = right.data;
            right.data = leftData;
        }

        public Node getNFirst() {
            if (size == 0) {
                System.out.println("List is empty");
            }
            return head;
        }

        public Node getNLast() {
            if (size == 0) {
                System.out.println("List is empty!");
            }
            return tail;
        }

        public Node getNAt(int idx) {
            if (size == 0) {
                System.out.println("List is empty!");
            }
            if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments!");
            }
            if (idx == 0)
                getNFirst();
            if (idx == size - 1)
                getNLast();
            Node curr = head;
            for (int i = 0; i < idx; i++) {
                curr = curr.next;
            }
            return curr;
        }

        public void reverseData1() {
            int left = 0, right = size - 1;
            while (left < right) {
                Node leftN = getNAt(left);
                Node rightN = getNAt(right);
                swap(leftN, rightN);
                left++;
                right--;
            }
        }

        // ! Reverse linkedlist - Data recursive
        public Node reverseLLrecursive(Node left, Node right, int counter) {
            // base
            if (left == null) {
                return right;
            }
            // left update in preorder
            right = reverseLLrecursive(left.next, right, counter + 1);
            // swapping in upperhalf/lower half
            if (counter < size / 2) {
                swap(left, right);
            }
            right = right.next;
            return right;
        }

        public void reverseData2() {
            Node left = head, right = head;
            reverseLLrecursive(left, right, 0);
        }

        // ! Reverse Linkedlist - Pointer/Node iterative

        public void reverseNode1() {
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
        // ! Reverse Linkedlist - Pointer/Node recursive

        private void reverseLLHelper(Node node) {
            // base case
            if (node == null || node.next == null) {
                return;
            }
            reverseLLHelper(node.next);
            node.next.next = node;
        }

        public void reverseNode2() {
            Node curr = head;
            reverseLLHelper(curr);
            // swapping of head & tail
            Node temp = head;
            head = tail;
            tail = temp;
            // at last made tail points to null
            tail.next = null;
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
            } else if (str.startsWith("addAt")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                int idx = Integer.parseInt(str.split(" ")[2]);
                list.addAt(val, idx);
            } else if (str.startsWith("removeFirst")) {
                list.removeFirst();
            } else if (str.startsWith("removeLast")) {
                list.removeLast();
            } else if (str.startsWith("removeAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                list.removeAt(idx);
            } else if (str.startsWith("getFirst")) {
                System.out.println(list.getFirst());
            } else if (str.startsWith("getLast")) {
                System.out.println(list.getLast());
            } else if (str.startsWith("getAt")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                System.out.println(list.getAt(idx));
            } else if (str.startsWith("size")) {
                list.size();
            } else if (str.startsWith("display")) {
                list.display();
            } else if (str.startsWith("reverseData1")) {
                list.reverseData1();
            } else if (str.startsWith("reverseData2")) {
                list.reverseData2();
            } else if (str.startsWith("reverseNode1")) {
                list.reverseNode1();
            } else if (str.startsWith("reverseNode2")) {
                list.reverseNode2();
            } else {
                System.out.println("Write correct input format!");
            }
            str = br.readLine();
        }

        testList(list);

    }
}