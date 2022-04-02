import java.io.*;
import java.util.*;

public class isTreeBalanced {

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

    // using static global/travel and change
    static boolean isBalanced = true;

    public static int isBalanced(Node root) {
        if (root == null)
            return 0;

        int leftH = isBalanced(root.left);
        int rightH = isBalanced(root.right);

        int gap = Math.abs(leftH - rightH);
        if (gap > 1)
            isBalanced = false;

        return Math.max(leftH, rightH) + 1;
    }

    // using pair class
    static class BPair {
        int height;
        boolean isBal;

        BPair() {
            this.height = 0;
            this.isBal = true;
        }
    }

    public static BPair isBalanced2(Node root) {
        if (root == null)
            return new BPair();

        BPair left = isBalanced2(root.left);
        BPair right = isBalanced2(root.right);

        // khud ka balance pair - hme bhi to apni height return krni h na, & hmari bhi
        // kisiko zarurat h
        BPair mp = new BPair();
        // hum khud balance tabhi honge, jab hum balance honge & hmara left & right bhi
        // balance hoga
        mp.isBal = Math.abs(left.height - right.height) <= 1 && left.isBal && right.isBal;
        mp.height = Math.max(left.height, right.height) + 1;
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
        // displayBTree(root);
        // isBalanced(root);
        BPair ans = isBalanced2(root);
        System.out.println(ans.isBal);
    }
}
