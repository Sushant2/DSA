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

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int n = Integer.parseInt(br.readLine());
        LinkedList list = new LinkedList();
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int d = Integer.parseInt(values[i]);
            list.addLast(d);
        }
        LinkedList sortedList = LinkedList.mergeSort(list.head);
        sortedList.display();
    }
}
