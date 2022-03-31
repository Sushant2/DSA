import java.io.*;
import java.util.*;

//TODO - IMP que : diameter of binary tree/generic tree n rest
//! DIAMETER Definition - 
//? maximum distance between 2 nodes/leafnodes(but almost in every case nodes are leaf nodes){in terms of edges}
//? it's not necessary that diameter passes through root node
public class diameterOfBinaryTree {
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

    // leaf node of maximum depth in left subtree
    // leaf node of maximum depth in right subtree
    // (dono maximum depths leafs ki, ek hi subtreem ho skti h)
    // distance b/w them is the diameter of Btree

    // ! BRUTE FORCE - 1stapproach - O(n^2)

    // height in terms of edge
    public static int height(Node node) {
        if (node == null)
            return -1;
        int lh = height(node.left);
        int rh = height(node.right);
        int th = Math.max(lh, rh) + 1;
        return th;
    }

    // Apply travel and change strategy -
    // 1. node par khade hokar node ka diameter nikalenge that is - leftheight +
    // rightheight + 2
    // 2. & sare diameter mein se jo sbse maxm diameter milega wahi poora tree ka
    // diameter h

    // ! 1st approach - we're returning diameter here
    //! O(n^2) - time comp
    public static int diameter(Node root) {
        if (root == null)
            return 0;
        int lDia = diameter(root.left);
        int rDia = diameter(root.right);

        // meeting expec
        int lHeight = height(root.left);
        int rHeight = height(root.right);

        int dia = lHeight + rHeight + 2;

        return Math.max(dia, Math.max(lDia, rDia));
    }

    // ! 2nd approach - we're returning height here & forming our answer in static
    // ! variable
    //! O(n) - time comp
    static int maxDia = 0;

    public static int diameter2(Node node) {
        if (node == null)
            return -1;
        int lHeight = diameter2(node.left);
        int rHeight = diameter2(node.right);

        int dia = lHeight + rHeight + 2;
        maxDia = Math.max(maxDia, dia);
        return (Math.max(lHeight, rHeight) + 1);
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
        // int dia = diameter(root);
        // System.out.println(dia);
        diameter2(root);
        System.out.println(maxDia);
    }
}
