import java.io.*;
import java.util.*;

public class iterativePreInPostTraversal {

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

    public static Node constructTree(Integer[] arr) {
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
            } else {
                // postorder
                stk.pop();
            }
        }
        return root;
    }

    public static void displayTree(Node root) {
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
        displayTree(root.left);
        // inorder
        displayTree(root.right);
        // postorder
    }

    public static void preInPostTraverse(Node root) {
        Stack<Pair> stk = new Stack<>();
        stk.push(new Pair(root, -1));
        String pre = "", in = "", post = "";
        while (!stk.isEmpty()) {
            Pair par = stk.peek();
            if (par.state == -1) {
                // preorder
                pre += par.node.data + " ";
                if (par.node.left != null)
                    stk.push(new Pair(par.node.left, -1));
                par.state++;
            } else if (par.state == 0) {
                // inorder
                in += par.node.data + " ";
                if (par.node.right != null)
                    stk.push(new Pair(par.node.right, -1));
                par.state++;
            } else {
                // postorder
                post += par.node.data + " ";
                stk.pop();
            }
        }
        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
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
        Node root = constructTree(arr);
        displayTree(root);
        preInPostTraverse(root);
    }
}
