import java.util.*;
import java.io.*;

//! SIZE & SUM can't be optimised so, remains same as in Binary Tree
//! MAX,MIN & FIND can be optimised in BST
public class sizeSumMaxMinFind {

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

    public static int size(Node root) {
        if (root == null)
            return 0;
        int lsize = size(root.left);
        int rsize = size(root.right);
        return lsize + rsize + 1;
    }

    public static int sum(Node root) {
        if (root == null)
            return 0;
        int lsum = sum(root.left);
        int rsum = sum(root.right);
        return root.data + lsum + rsum;
    }

    public static int max(Node root) {
        if (root.right == null)
            return root.data;
        return max(root.right);
    }

    public static int min(Node root) {
        if (root.left == null)
            return root.data;
        return min(root.left);
    }

    // for FIND, we'll use binary search/divide and conquer in binary search tree

    public static boolean find(Node root, int data) {
        if (root == null)
            return false;
        if (root.data == data)
            return true;
        if (data > root.data)
            return find(root.right, data);
        else
            return find(root.left, data);
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
        int data = Integer.parseInt(br.readLine());
        Node root = constructBTree(arr);
        System.out.println(size(root));
        System.out.println(sum(root));
        System.out.println(max(root));
        System.out.println(min(root));
        System.out.println(find(root, data));
    }
}
