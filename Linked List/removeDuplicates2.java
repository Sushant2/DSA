//? delete all nodes that have duplicates numbers, leaving only distinct numbers from original list

import java.util.*;
import java.io.*;

public class removeDuplicates2 {

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
    public static Node deleteDuplicates(Node head) {
        Node dummy = new Node(-1);
        Node tail = dummy;
        while (head != null) {
            // single occurence
            if (head.next == null || head.next.data != head.data) {
                tail.next = head;
                tail = head;
                head = head.next;
            } else {
                // delete all multiple occurences
                Node curr = head;
                while (curr != null && curr.data == head.data) {
                    curr = curr.next;
                }
                head = curr;
            }
        }
        tail.next = null;
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
        Node node = deleteDuplicates(list.head);
        display(node);
    }
}