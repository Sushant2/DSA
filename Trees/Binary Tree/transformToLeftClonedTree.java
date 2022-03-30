import java.io.*;
import java.util.*;

public class transformToLeftClonedTree {
    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node constructBTree(Integer[] arr) {
        Node root = new Node(arr[0]);
        Stack<Pair> stk = new Stack<>();
        stk.push(new Pair(root, -1));
        int idx = 0;
        while (!stk.isEmpty()) {
            Pair par = stk.peek();
            if (par.state == -1) {
                // preorder
                idx++;
                if (arr[idx] != null) {
                    Node child = new Node(arr[idx]);
                    par.node.left = child;
                    stk.push(new Pair(child, -1));
                }
                par.state++;
            } else if (par.state == 0) {
                // inorder
                idx++;
                if (arr[idx] != null) {
                    Node child = new Node(arr[idx]);
                    par.node.right = child;
                    stk.push(new Pair(child, -1));
                }
                par.state++;
            } else
                stk.pop();
        }
        return root;
    }

    public static void displayBTree(Node root) {
        if (root == null)
            return;
        if (root.left != null)
            System.out.print(root.left.data);
        else
            System.out.print(".");
        System.out.print(" <- " + root.data + " -> ");
        if (root.right != null)
            System.out.print(root.right.data);
        else
            System.out.print(".");

        System.out.println();
        // preorder
        displayBTree(root.left);
        // inorder
        displayBTree(root.right);
        // postorder
    }

    // ! using node return type
    public static Node transformToLeftClone(Node root) {
        if (root == null)
            return null;

        Node leftRoot = transformToLeftClone(root.left);
        Node rightRoot = transformToLeftClone(root.right);

        // new node
        Node copyNode = new Node(root.data);
        copyNode.left = leftRoot;
        root.left = copyNode;
        return root;
    }

    // ! using void return type
    public static void transformToLeftClone2(Node root) {
        if (root == null)
            return;
        transformToLeftClone2(root.left);
        transformToLeftClone(root.right);
        Node copyNode = new Node(root.data);
        copyNode.left = root.left;
        root.left = copyNode;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            if (values[i].equals("n") == false)
                arr[i] = Integer.parseInt(values[i]);
            else
                arr[i] = null;
        }
        Node root = constructBTree(arr);
        // Node transformedRoot = transformToLeftClone(root);
        // displayBTree(transformedRoot);
        transformToLeftClone2(root);
        displayBTree(root);
    }
}