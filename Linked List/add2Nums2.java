import java.io.*;
import java.util.*;

public class add2Nums2 {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        public void display() {
            Node curr = head;
            while (curr != null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            System.out.println();
        }

        public void addLast(int val) {
            Node temp = new Node(val);
            if (size == 0) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }
            size++;
        }

        public void addFirst(int val) {
            Node temp = new Node(val);
            if (size == 0) {
                head = tail = temp;
            } else {
                temp.next = head;
                head = temp;
            }
            size++;
        }
    }

    public static void displayList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static Node reverse(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node ahead = curr.next;
            curr.next = prev;
            prev = curr;
            curr = ahead;
        }
        return prev;
    }

    public static Node add2Nums(Node head1, Node head2) {
        head1 = reverse(head1);
        head2 = reverse(head2);
        Node dummy = new Node(-1);
        Node head = dummy, tail = dummy;
        int carry = 0;
        while (carry != 0 || head1 != null || head2 != null) {
            int d1 = (head1 == null) ? 0 : head1.data;
            int d2 = (head2 == null) ? 0 : head2.data;
            Node temp = new Node((d1 + d2 + carry) % 10);
            carry = (d1 + d2 + carry) / 10;
            tail.next = temp;
            tail = temp;
            if (head1 != null)
                head1 = head1.next;
            if (head2 != null)
                head2 = head2.next;
        }
        return reverse(dummy.next);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();
        int n1 = Integer.parseInt(br.readLine());
        String[] values1 = br.readLine().split(" ");
        for (int i = 0; i < n1; i++) {
            l1.addLast(Integer.parseInt(values1[i]));
        }
        int n2 = Integer.parseInt(br.readLine());
        String[] values2 = br.readLine().split(" ");
        for (int i = 0; i < n2; i++) {
            l2.addLast(Integer.parseInt(values2[i]));
        }
        Node res = add2Nums(l1.head, l2.head);
        displayList(res);
    }
}
