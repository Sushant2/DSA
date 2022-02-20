import java.io.*;
import java.util.*;

public class subtract2LL {
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
            if (size == 0) {
                head = tail = temp;
            } else {
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

    public static Boolean findLarger(Node l1, Node l2) {
        // count
        int count1 = 0, count2 = 0;
        Node t1 = l1, t2 = l2;
        while (l1 != null) {
            count1++;
            l1 = l1.next;
        }
        while (l2 != null) {
            count1++;
            l2 = l2.next;
        }
        if (count1 > count2) {
            return true;
        } else if (count1 < count2) {
            return true;
        }
        // if size of both lists are equal
        // compare ones, tens, hundreds
        while (t1 != null || t2 != null) {
            if (t1.data > t2.data) {
                return true;
            } else if (t1.data < t2.data) {
                return false;
            }
            t1 = t1.next;
            t2 = t2.next;
        }
        return true;

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

    public static Node subtractLL(Node l1, Node l2) {
        // edge case - to remove zeroes at front
        while (l1.next != null && l1.data == 0)
            l1 = l1.next;
        while (l2.next != null && l2.data == 0)
            l2 = l2.next;

        // find larger list
        if (findLarger(l1, l2) == false) {
            Node temp = l1;
            l1 = l2;
            l2 = temp;
        }

        l1 = reverse(l1);
        l2 = reverse(l2);

        // now create dummy node to return result
        Node dummy = new Node(-1);
        Node head = dummy, tail = dummy;
        int borrow = 0;
        while (l1 != null || l2 != null) {
            int d1 = (l1 == null) ? 0 : l1.data;
            int d2 = (l2 == null) ? 0 : l2.data;
            int diff = d1 - d2 + borrow;
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }
            Node temp = new Node(diff);
            tail.next = temp;
            tail = temp;
            if (l1 != null) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
        }
        dummy = reverse(dummy.next);
        while (dummy.next != null && dummy.data == 0) {
            dummy = dummy.next;
        }
        return dummy;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();

        int n = Integer.parseInt(br.readLine());
        String[] values1 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            l1.addLast(Integer.parseInt(values1[i]));
        }
        int m = Integer.parseInt(br.readLine());
        String[] values2 = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            l2.addLast(Integer.parseInt(values2[i]));
        }
        Node subNode = subtractLL(l1.head, l2.head);
        display(subNode);
    }
}
