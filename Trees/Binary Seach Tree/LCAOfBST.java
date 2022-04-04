import java.io.*;
import java.util.*;

//LEETCODE - 235
public class LCAOfBST {

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

    // time complexity - O(H), space comp - O(1)
    public static Node LCA(Node root, int d1, int d2) {
        // base case
        if (root == null)
            return null;
        // if one node is parent of other
        if (d1 == root.data || d2 == root.data)
            return root;
        else if (d1 < root.data && d2 < root.data) {
            return LCA(root.left, d1, d2);
        } else if (d1 > root.data && d2 > root.data) {
            return LCA(root.right, d1, d2);
        } else {
            // d1<root.data<d2
            // d2<root.data<d1
            return root;
        }
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
        // display(root);
        int d1 = Integer.parseInt(br.readLine());
        int d2 = Integer.parseInt(br.readLine());
        Node lca = LCA(root, d1, d2);
        System.out.println(lca.data);

    }
}
