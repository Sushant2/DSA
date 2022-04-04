import java.io.*;
import java.util.*;

public class replaceWithSumOfLarger {

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node constructBST(int[] arr, int low, int right) {
        if (low > right)
            return null;
        int mid = (low + right) / 2;
        Node root = new Node(arr[mid]);
        root.left = constructBST(arr, low, mid - 1);
        root.right = constructBST(arr, mid + 1, right);
        return root;
    }

    public static void display(Node root) {
        if (root == null)
            return;
        display(root.left);
        System.out.print(root.data + " ");
        display(root.right);
    }

    // we'll trverse from back that is reverse Inorder traverseal & update the data
    // while traversing
    // ! PEPCODING que - replace a node's value with the sum of all nodes greater
    // ! than it
    static int sum = 0;

    public static Node smallToGreaterTree(Node root) {
        if (root == null)
            return null;
        smallToGreaterTree(root.right);
        int temp = root.data;
        root.data = sum;
        sum += temp;
        smallToGreaterTree(root.left);

        return root;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(values[i]);
        Node root = constructBST(arr, 0, n - 1);
        display(root);
        root = smallToGreaterTree(root);
        System.out.println();
        display(root);
    }
}
