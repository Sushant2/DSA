import java.io.*;
import java.util.*;

public class oddEvenValuesLL {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static class LinkedList {
        Node head, tail;
        int size;

        public int size() {
            return size;
        }

        public void addLast(int val) {
            Node temp = new Node(val);
            if (size == 0)
                head = tail = temp;
            else {
                tail.next = temp;
                tail = temp;
            }
            size++;
        }
    }

    public static void display(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    // ! variation of sort 01

    public static Node oddEven(Node head) {
        Node odd = new Node(-1);
        Node even = new Node(-1);
        Node otail = odd, etail = even;
        while (head != null) {
            if (head != null && head.data % 2 == 0) {
                etail.next = head;
                etail = head;
            }
            if (head != null && head.data % 2 != 0) {
                otail.next = head;
                otail = head;
            }
            head = head.next;
        }
        // if even node vals comes before odd
        otail.next = null;
        etail.next = odd.next;
        return even.next;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();
        int n = Integer.parseInt(br.readLine());
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            list.addLast(Integer.parseInt(values[i]));
        }
        Node node = oddEven(list.head);
        display(node);
    }
}
