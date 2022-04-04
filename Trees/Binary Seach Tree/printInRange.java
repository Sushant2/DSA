import java.io.*;
import java.net.Inet4Address;
import java.util.*;

public class printInRange {

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }

    }

    public static Node construct(int[] arr, int low, int high) {
        // base case
        if (low > high)
            return null;
        int mid = (low + high) / 2;
        Node root = new Node(arr[mid]);
        root.left = construct(arr, low, mid - 1);
        root.right = construct(arr, mid + 1, high);
        return root;
    }

    public static void display(Node root) {
        if (root == null)
            return;
        display(root.left);
        System.out.print(root.data + " ");
        display(root.right);
    }

    // print in range without optimization
    // !time comp - O(n)
    public static void printinrange(Node root, int d1, int d2) {
        if (root == null)
            return;

        printinrange(root.left, d1, d2);
        if (root.data >= d1 && root.data <= d2)
            System.out.print(root.data + " ");
        printinrange(root.right, d1, d2);
    }

    // with optimization - saving calls
    // ! time comp - best case - O(h), worst case - O(n)
    public static void printinrange2(Node node, int d1, int d2) {
        if (node == null)
            return;
        if (node.data < d1)
            printinrange2(node.right, d1, d2);
        else if (node.data > d2)
            printinrange2(node.right, d1, d2);
        else {
            printinrange2(node.left, d1, d2);
            System.out.print(node.data + " ");
            printinrange2(node.right, d1, d2);
        }

    }

    // code variation of optimised code
    public static void printinrange3(Node node, int d1, int d2) {
        if (node == null)
            return;
        if (!(node.data < d1))
            printinrange3(node.left, d1, d2);
        if (node.data >= d1 && node.data <= d2)
            System.out.print(node.data + " ");
        if (!(node.data > d2))
            printinrange3(node.right, d1, d2);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String values[] = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        Node root = construct(arr, 0, n - 1);
        int d1 = Integer.parseInt(br.readLine());
        int d2 = Integer.parseInt(br.readLine());
        // display(root);
        // printinrange(root, d1, d2);
        // printinrange2(root, d1, d2);
        printinrange3(root, d1, d2);
    }
}
