import java.io.*;
import java.util.*;

public class unfoldLL {
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

        public void addFirst(int val) {
            Node temp = new Node(val);
            if (size == 0)
                head = tail = temp;
            else {
                temp.next = head;
                head = temp;
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

    public static Node reverseLL(Node head) {
        Node curr = head, prev = null;
        while (curr != null) {
            Node ahead = curr.next;
            curr.next = prev;
            prev = curr;
            curr = ahead;
        }
        return prev;
    }

    // ! in O(n) - time complexity
    public static Node unfoldList(Node head) {
        // ? 2 dummy nodes to sepearate list
        // ? reverse 2nd one
        // ? add it in first
        Node first = new Node(-1);
        Node ftail = first;
        Node second = new Node(-1);
        Node stail = second;
        while (head != null) {
            ftail.next = head;
            ftail = head;
            head = head.next;
            if (head != null) {
                stail.next = head;
                stail = head;
                head = head.next;
            }
        }
        stail.next = null;
        ftail.next = reverseLL(second.next);
        return first.next;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();
        int n = Integer.parseInt(br.readLine());
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            list.addLast(Integer.parseInt(values[i]));
        }
        Node node = unfoldList(list.head);
        display(node);
    }
}
