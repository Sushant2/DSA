import java.util.*;
import java.io.*;

public class addNodeToBST {

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

    public static Node addNode(Node root, int data) {
        if (root == null) {
            Node newNode = new Node(data);
            return newNode;
        }
        // if node is already present in the tree return
        if (root.data == data)
            return root;
        else if (root.data < data)
            root.right = addNode(root.right, data);
        else
            root.left = addNode(root.left, data);

        return root;
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
        addNode(root, data);
        System.out.println();
        display(root);
    }
}
