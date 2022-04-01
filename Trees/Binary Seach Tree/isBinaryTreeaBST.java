import java.io.*;
import java.util.*;

//! Properties of binary trees
//? 1. the leftsubtree of a node contains only nodes with values less than the node's value
//? 2. the rightsubtree of a node contains only nodes with values greater than the node's value

public class isBinaryTreeaBST {

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

    // ! 3 approaches -
    // First :(PostOrder) check for a node, all nodes in it's left subtree are
    // smaller than the node
    // & all nodes in it's right subtree are greater than the node
    // Second :(Inorder) inorder traversal of binary search tree is in increasing
    // order, so check inorder traversal of binary tree
    // Third : (Preorder) check for each node in preorder that it lies in range of
    // (min,max), left jayenge to max update krenge, right jayenge to min updte
    // krenge

    // ? First : postorder mein iisliye - suppose, for 50, 50 ye mangega kimein apne
    // leftsubtree mein se bada hu, & rightsubtree se ye mangega ki mein sbse chota
    // hu

    // ! Optimised appraoch - O(n)
    static class isBSTPair {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        boolean isbst = true;
    }

    public static isBSTPair isBST(Node root) {
        if (root == null)
            return new isBSTPair();

        isBSTPair left = isBST(root.left);
        isBSTPair right = isBST(root.right);

        // pahle check krlo kya khud BST ka part ho
        isBSTPair curr = new isBSTPair();
        if (right.min > root.data && left.max < root.data && left.isbst && right.isbst)
            curr.isbst = true;
        else
            curr.isbst = false;
        curr.min = Math.min(root.data, Math.min(left.min, right.min));
        curr.max = Math.max(root.data, Math.max(left.max, right.max));
        return curr;

    }

    // ! worst time comp - O(n^2) - apprach w/o pair class
    // public static boolean isBST1(Node root) {
    // if (root == null) {
    // return true;
    // }
    // boolean lres = isBST1(root.left);
    // boolean rres = isBST1(root.right);
    // int leftMax = max(root.left);
    // int rightMin = min(root.right);

    // if (leftMax < root.data && root.data < rightMin)
    // return true;
    // return false;
    // }

    // ? 2nd Apporach : inorder traversal
    static int prev = Integer.MIN_VALUE;

    public static boolean isBST2(Node root) {
        if (root == null) {
            return true;
        }
        // preorder
        boolean left = isBST2(root.left);
        // inorder
        // khud ka kaam
        boolean self = true;
        if (root.data < prev)
            self = false;
        else
            prev = root.data;
        // postorder
        boolean right = isBST2(root.right);
        return left && right && self;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // !we're creating array using non primitive data type/class cos, "Integer"
        // class can also store/hold null values
        Integer[] arr = new Integer[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            if (values[i].equals("n") == false)
                arr[i] = Integer.parseInt(values[i]);
            else
                arr[i] = null;
        }
        Node root = constructBTree(arr);
        // isBSTPair ans = isBST(root);
        boolean ans = isBST2(root);
        if (ans)
            System.out.println("true");
        else
            System.out.println("false");
    }
}
