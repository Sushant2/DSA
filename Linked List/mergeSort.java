import java.io.*;
import java.util.*;

public class mergeSort {
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

        public void addFirst(int val) {
            Node temp = new Node(val);
            // temp.data = val;
            if (size == 0)
                head = tail = temp;
            else {
                temp.next = head;
                head = temp;
            }
            size++;
        }

        public void addLast(int val) {
            Node temp = new Node(val);
            // temp.data = val;
            if (size == 0)
                head = tail = temp;
            else {
                tail.next = temp;
                tail = temp;
            }
            size++;
        }

        public void display() {
            Node curr = head;
            while (curr != null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            System.out.println();
        }
    }

    public static void displayList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static Node middle(Node head) {
        Node slow = head, fast = head;
        Node prev = null; // to store previous slow for even list
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null)
            return prev;
        return slow;
    }

    public static Node merge2SortedLL(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        Node dummy = new Node(-1);
        Node head = dummy, tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        if (head1 != null)
            tail.next = head1;
        else
            tail.next = head2;
        return dummy.next;
    }

    public static Node mergeSortLL(Node head) {
        // edge case - if list is empty or with 1 node only
        if (head == null || head.next == null) {
            return head;
        }
        // find middle node
        Node mid = middle(head);
        // make 2 lists
        Node midNext = mid.next;
        mid.next = null;

        Node left = mergeSortLL(head);
        Node right = mergeSortLL(midNext);
        return merge2SortedLL(left, right);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        LinkedList list = new LinkedList();
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int d = Integer.parseInt(values[i]);
            list.addLast(d);
        }
        // LinkedList sortedList = LinkedList.mergeSortLL(list.head);
        Node sortedLL = mergeSortLL(list.head);
        // display(sortedLL);
        displayList(sortedLL);
    }
}
