//! GFG - add 1 to LinkedList

import java.io.*;
import java.util.*;

public class add1toLL {
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

    public static Node addOne(Node head) {
        head = reverse(head);
        int d1 = (head == null) ? 0 : head.data;
        if ((d1 + 1) <= 9) {
            head.data += 1;
            return reverse(head);
        } else {
            Node dummy = new Node(-1);
            Node tail = dummy;
            int carry = 0, count = 1;
            while (carry != 0 || head != null) {
                d1 = (head == null) ? 0 : head.data;
                if (count-- > 0)
                    d1 += 1;
                Node temp = new Node((d1 + carry) % 10);
                carry = (d1 + carry) / 10;
                tail.next = temp;
                tail = temp;

                if (head != null)
                    head = head.next;
            }
            return reverse(dummy.next);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();
        int n = Integer.parseInt(br.readLine());
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            list.addLast(Integer.parseInt(values[i]));
        }
        Node res = addOne(list.head);
        displayList(res);
    }
}
