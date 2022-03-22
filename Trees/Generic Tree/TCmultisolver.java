//!TC - Travel & Change Strategy Problems
//!Travel & Change/Solve - make changes as we traverse in the tree simultaneously

import java.io.*;
import java.util.*;

public class TCmultisolver {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    public static Node constructTree(int[] arr) {
        Node root = null;
        Stack<Node> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1)
                stk.pop();
            else {
                Node curr = new Node(arr[i]);
                if (stk.size() == 0)
                    root = curr;
                else
                    stk.peek().children.add(curr);
                stk.push(curr);
            }
        }
        return root;
    }

    public static void displayTree(Node root) {
        System.out.print(root.data + " -> ");
        for (Node child : root.children)
            System.out.print(child.data + ", ");
        System.out.println(".");
        for (Node child : root.children)
            displayTree(child);
    }

    // ! return type - void
    static int size = 0;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int height = 0;

    public static void multiSolver1(Node root, int level) {
        if (root == null)
            return;

        // preorder
        size++;
        if (root.data < min)
            min = root.data;
        if (root.data > max)
            max = root.data;
        if (level > height)
            height = level;
        for (Node child : root.children)
            multiSolver1(child, level + 1);
    }

    // ! return type - class
    public static class multisolver {
        int size = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int height = 0;

        multisolver(int size, int min, int max) {
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    public static multisolver multiSolver2(Node root) {
        multisolver ans = new multisolver(1, root.data, root.data);
        // initialse - commented cos, created constructor
        // ans.size = 1;
        // ans.max = root.data;
        // ans.min = root.data;
        for (Node child : root.children) {
            multisolver temp = multiSolver2(child);
            ans.size += temp.size;
            ans.max = Math.max(ans.max, temp.max);
            ans.min = Math.min(ans.min, temp.min);
            ans.height = Math.max(ans.height, temp.height + 1);
        }
        return ans;
    }

    // ! return type - void, passing an array - a[0] as size, a[1] as min, a[2] as
    // max, a[3] as height
    public static void multiSolver3(Node root, int[] ans, int level) {
        ans[0]++;
        if (root.data < ans[1])
            ans[1] = root.data;
        if (root.data > ans[2])
            ans[2] = root.data;
        if (level > ans[3])
            ans[3] = level;
        for (Node child : root.children) {
            multiSolver3(child, ans, level + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }

        Node root = constructTree(arr);
        // displayTree(root);
        // multiSolver1(root, 0);
        // multisolver ans = multiSolver2(root);
        int[] ans = { 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
        multiSolver3(root, ans, 0);
        System.out.println("Size: " + ans[0]);
        System.out.println("Max: " + ans[2]);
        System.out.println("Min: " + ans[1]);
        System.out.println("Height: " + ans[3]);
    }

}