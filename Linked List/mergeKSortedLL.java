import java.io.*;
import java.util.*;

public record mergeKSortedLL() {
    public static Scanner scn = new Scanner(System.in);

    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static class LinkedList {
        int size;
        Node head;
        Node tail;

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

    // ! Brute Force - O(N*k) or O(n*k*k)
    // ? find mininum head node from all Lists!
    // ? do the same as we do in case of merging 2 sorted LL

    public static int minIdx(Node[] lists) {
        int idx = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null && lists[i].data < min) {
                min = lists[i].data;
                idx = i;
            }
        }
        return idx;
    }

    public static Node mergeKLists1(Node[] lists) {
        if (lists.length == 0)
            return null;
        // create dummy node
        Node dummy = new Node(-1);
        Node head = dummy, tail = dummy;
        while (true) {
            int idx = minIdx(lists);
            if (idx == -1)
                break;
            tail.next = lists[idx];
            lists[idx] = lists[idx].next;
            tail = tail.next;
        }
        return dummy.next;
    }

    // ! Optimised - O(k*n*log(k))
    // ? using divide & conquere approach with merge 2 sorted list technique

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

    public static Node helper(Node[] lists, int left, int right) {
        if (left > right)
            return null;
        if (left == right)
            return lists[left];
        int mid = (left + right) / 2;
        Node l1 = helper(lists, left, mid);
        Node l2 = helper(lists, mid + 1, right);
        return merge2SortedLL(l1, l2);
    }

    public static Node mergeKLists2(Node[] lists) {
        // edge case - when 0 LL
        if (lists.length == 0)
            return null;
        return helper(lists, 0, lists.length - 1);
    }

    public static Node createList(int size) {
        Node dummy = new Node(-1);
        Node prev = dummy;
        while (size-- > 0) {
            prev.next = new Node(scn.nextInt());
            prev = prev.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) throws Exception {
        int n = scn.nextInt();
        Node[] list = new Node[n];
        for (int i = 0; i < n; i++) {
            int m = scn.nextInt();
            list[i] = createList(m);
        }
        // Node head = mergeKLists1(list);
        Node head = mergeKLists2(list);

        displayList(head);
    }
}