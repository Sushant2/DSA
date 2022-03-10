//! ALSO KNOWN AS BFS - breadth first search/traversal

import java.io.*;
import java.util.*;

public class levelOrderTraversal {

    private static class Node {
        int data;
        ArrayList<Node> children;

        Node() {
            this.data = 0;
            this.children = new ArrayList<>();
        }

        Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    public static Node constructATree(int[] arr) {
        Node root = null;
        Stack<Node> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1)
                stk.pop();
            else {
                Node temp = new Node();
                temp.data = arr[i];
                if (stk.size() == 0)
                    root = temp;
                else
                    stk.peek().children.add(temp);
                stk.push(temp);
            }
        }
        return root;
    }

    public static void displayTree(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + ": ");
        for (Node child : node.children)
            System.out.print(child.data + ", ");
        // faith
        for (Node child : node.children)
            displayTree(child);
    }

    public static void levelOrderTrav(Node node) {
        // need a queue
        Queue<Node> que = new ArrayDeque<>();
        que.add(node);
        while (que.size() != 0) {
            // remove
            Node curr = que.remove();
            System.out.print(curr.data + " ");
            // add it's children in queue - jo pop kia use k child push krne hai queue m
            for (Node child : curr.children)
                que.add(child);
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(values[i]);
        Node root = constructATree(arr);
        // displayTree(root);
        levelOrderTrav(root);
    }
}
