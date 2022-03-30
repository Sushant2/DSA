import java.util.*;
import java.io.*;

public class removeLeavesInBTree {

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

    public static Node constructATree(Integer[] arr) {
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
                // postorder
                stk.pop();
        }
        return root;
    }

    public static void displayBtree(Node root) {
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

        displayBtree(root.left);
        displayBtree(root.right);
    }

    // ! void return type can't handle an edge case where - where root is also a
    // ! leaf node i.e., only one node
    // ? preorder - kyoki hum uni nodes par jaynege jp delete hone ke baad bachi
    // ?hongi
    public static void removeLeaves(Node root) {
        if (root == null)
            return;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null)
                root.left = null;
        }
        if (root.right != null) {
            if (root.right.left == null && root.right.right == null)
                root.right = null;
        }

        removeLeaves(root.left);
        removeLeaves(root.right);
    }

    // ! 2nd code variation - void type
    public static void removeLeaves2(Node root) {
        if (root == null)
            return;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null)
                root.left = null;
            else
                removeLeaves2(root.left);
        }
        if (root.right != null) {
            if (root.right.left == null && root.right.right == null)
                root.right = null;
            else
                removeLeaves2(root.right);
        }
    }

    public static Node removeLeaves3(Node root) {
        if (root == null)
            return null;
        // leaf node
        if (root.left == null && root.right == null)
            return null;
        Node leftChild = removeLeaves3(root.left);
        Node rightChild = removeLeaves3(root.right);

        root.left = leftChild;
        root.right = rightChild;

        // hum leaf node nhi h, to khud ko return krado - nonleaf node
        return root;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[n];
        String values[] = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            if (values[i].equals("n") == false)
                arr[i] = Integer.parseInt(values[i]);
            else
                arr[i] = null;
        }
        Node root = constructATree(arr);
        // removeLeaves(root);
        // removeLeaves2(root);
        root = removeLeaves3(root);
        displayBtree(root);
    }
}
