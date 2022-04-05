import java.io.*;
import java.util.*;

public class targetSumPairInBST {

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

    // !first approach : Inorder & find
    // time comp : O(n*h) + space comp : O(h)
    public static boolean find(Node root, int data) {
        if (root == null)
            return false;
        if (root.data > data)
            return find(root.left, data);
        else if (root.data < data)
            return find(root.right, data);
        else
            return true;
    }

    public static void targetSum(Node curr, int sum, Node root) {
        if (curr == null)
            return;
        targetSum(curr.left, sum, root);
        // inorder
        // stop when data > sum/2
        if (curr.data >= (sum / 2))
            return;
        int data = sum - curr.data;
        // ! NOTICE :we're passing root to find, not curr node
        if (find(root, data))
            System.out.println(curr.data + " " + data);
        targetSum(curr.right, sum, root);
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
        int target = Integer.parseInt(br.readLine());
        display(root);
        targetSum(root, target, root);
    }
}
