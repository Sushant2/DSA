import java.util.*;
import java.io.*;

public class largestBSTSubtree {
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

    // print like this - largestBSTSubtreeROOT@size
    public static class BPair {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean isBST = true;
        int size = 0;
        Node root = null;
    }

    public static BPair largestBST(Node root) {
        if (root == null)
            return new BPair();

        BPair left = largestBST(root.left);
        BPair right = largestBST(root.right);

        // khud ka kaam
        BPair mp = new BPair();
        mp.isBST = root.data > left.max && root.data < right.min && left.isBST && right.isBST;
        mp.min = Math.min(root.data, Math.min(left.min, right.min));
        mp.max = Math.max(root.data, Math.min(left.max, right.max));
        // if we're BST
        if (mp.isBST) {
            mp.root = root;
            mp.size = left.size + right.size + 1;
        } else if (left.size > right.size) {
            mp.root = left.root;
            mp.size = left.size;
        } else {
            mp.root = right.root;
            mp.size = right.size;
        }
        return mp;
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
        Node root = constructBTree(arr);
        BPair ans = largestBST(root);
        System.out.println(ans.root.data + "@" + ans.size);
    }
}
