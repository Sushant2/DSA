import java.io.*;
import java.util.*;

//? Print all such nodes which are only child of their parent

public class printSingleChildNodes {

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

    // ! 1st code variation of - w/o need of taking parent node
    // 1 children wale node k child ko print kra
    // rhe hai, isiliye alag se parent node lene ki zarurat nhi h
    public static void printSingleChild(Node root) {
        // root null to hoga, agar left pr child h, to right k lie root nhi hoga
        if (root == null)
            return;
        if (root.left != null && root.right == null)
            System.out.println(root.left.data);
        else if (root.left == null && root.right != null)
            System.out.println(root.right.data);
        printSingleChild(root.left);
        printSingleChild(root.right);
    }

    // ! 2nd code variation of - w/o need of taking parent node
    public static void printSingleChild2(Node root) {
        if (root == null)
            return;

        if (root.left == null && root.right == null)
            // left node
            return;
        if (root.left == null)
            System.out.println(root.right.data);
        if (root.right == null)
            System.out.println(root.left.data);
        printSingleChild2(root.left);
        printSingleChild2(root.right);
    }

    // ! 3rd code variation of - w/o need of taking parent node
    // ? using XOR - (in xor, same wale false, different wale true)
    public static void printSingleChild3(Node root) {
        if (root == null)
            return;
        if ((root.left == null) ^ (root.right == null)) {
            if (root.left == null)
                System.out.println(root.right.data);
            if (root.right == null)
                System.out.println(root.left.data);
        }
        printSingleChild3(root.left);
        printSingleChild3(root.right);
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
        // printSingleChild(root);
        // printSingleChild2(root);
        printSingleChild3(root);
    }
}
