import java.io.*;
import java.util.*;

public class foldLL {
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

    public static Node middleLL(Node head) {
        Node slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null)
            return prev;
        return slow;
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
    public static Node foldList(Node head) {
    // ? find middle
    // ? reverse 2nd part
    // ? update list node by node
    Node mid = middleLL(head);
    Node second = reverseLL(mid.next);mid.next=null;

    // ? create dummy node for res
    Node dummy = new Node(-1);
    Node tail = dummy;while(head!=null||second!=null)
    {
        if (head != null) {
            tail.next = head;
            tail = head;
            head = head.next;
        }
        if (second != null) {
            tail.next = second;
            tail = second;
            second = second.next;
        }
    }tail.next=null;return dummy.next;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();
        int n = Integer.parseInt(br.readLine());
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            list.addLast(Integer.parseInt(values[i]));
        }
        Node node = foldList(list.head);
        display(node);
    }
}
