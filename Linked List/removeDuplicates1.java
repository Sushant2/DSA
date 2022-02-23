//? Delete all duplicates such that each element appears only once

import java.util.*;
import java.io.*;

public class removeDuplicates1 {

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

    // ! In O(n) - time complexity
    public static Node removeDuplicates(Node head) {
        Node dummy = new Node(-1);
        Node tail = dummy;
        while (head != null) {
            if (tail == dummy || head.data != tail.data) {
                tail.next = head;
                tail = head;
            }
            head = head.next;
        }
        tail.next = null;
        // head = dummy.next;
        return dummy.next;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();
        int n = Integer.parseInt(br.readLine());
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            list.addLast(Integer.parseInt(values[i]));
        }
        Node node = removeDuplicates(list.head);
        display(node);
    }
}