import java.util.*;
import java.io.*;

public class sizeSumMaxHeight {

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

    public static int size(Node root) {
        if (root == null)
            return 0;
        // int size = 1;
        // size += size(root.left);
        // size += size(root.right);

        // return size;

        // ! or - both method are same(post order)
        int leftSize = size(root.left);
        int rightSize = size(root.right);

        return 1 + leftSize + rightSize;
    }

    public static int sum(Node root) {
        if (root == null)
            return 0;
        // int sum = root.data;
        // sum += sum(root.left);
        // sum += sum(root.right);

        // return sum;

        // ! or - both are same(in postorder)
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        return root.data + leftSum + rightSum;
    }

    public static int Max(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;
        // int max = root.data;
        // max = Math.max(max, Max(root.left));
        // max = Math.max(max, Max(root.right));

        // return max;

        // ! or - both are same(post order)
        int leftMax = Max(root.left);
        int rightMax = Max(root.right);

        return Math.max(root.data, Math.max(leftMax, rightMax));
    }

    public static int height(Node root) {
        if (root == null)
            return 0;
        int height = 0;
        height = Math.max(height, height(root.left));
        height = Math.max(height, height(root.right));

        return height + 1;
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
        displayBtree(root);
        System.out.println("Size of Binary Tree: " + size(root));
        System.out.println("Sum of Binary Tree: " + sum(root));
        System.out.println("Maximum of Binary Tree: " + Max(root));
        System.out.println("Height of Binary Tree: " + height(root));

    }

}
