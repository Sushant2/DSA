import java.util.*;
import java.io.*;

public class removeNodeFromBST {

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

    public static int max(Node node) {
        if (node.right != null)
            return max(node.right);
        return node.data;
    }

    public static Node removeNode(Node node, int data) {
        if (node == null)
            // data/node does not exist
            return null;
        if (node.data == data) {
            // if node is leaf node
            if (node.left == null && node.right == null)
                return null;
            // for node having one child
            else if (node.left == null)
                // node having only right child
                return node.right;
            else if (node.right == null)
                // node having only left child
                return node.left;
            else {
                // node having 2 childs
                // look for its's max of left subtree(inorder predecessor) or it's min of right
                // subtree(inorder successor)
                int inorderPredecessor = max(node.left);
                node.data = inorderPredecessor;
                node.left = removeNode(node.left, inorderPredecessor);
            }

        } else if (node.data > data)
            node.left = removeNode(node.left, data);
        else
            node.right = removeNode(node.right, data);
        return node;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String values[] = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        int data = Integer.parseInt(br.readLine());
        Node root = construct(arr, 0, n - 1);
        display(root);
        root = removeNode(root, data);
        System.out.println();
        display(root);
    }
}
