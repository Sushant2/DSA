//? All 3 approaches to print level order linewise will have same
//! time comp - O(n)
//! space comp - O(n), in general in worst case

import java.util.*;
import java.io.*;

public class levelOrderTraversalLinewise {

    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

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
                Node temp = new Node(arr[i]);
                if (stk.size() == 0)
                    root = temp;
                else
                    stk.peek().children.add(temp);
                stk.push(temp);
            }
        }
        return root;
    }

    public static void displayTreeDFS(Node node) {
        System.out.print(node.data + " -> ");
        for (Node child : node.children)
            System.out.print(child.data + ", ");
        System.out.println();
        // faith
        for (Node child : node.children)
            displayTreeDFS(child);
    }

    // ! approach 1 => using 2 Queues - Main Queue & Child Queue
    public static void levelOrderLinewise1(Node node) {
        // mq - main queue & cq - child queue
        Queue<Node> mq = new ArrayDeque<>();
        Queue<Node> cq = new ArrayDeque<>();
        mq.add(node);
        while (mq.size() != 0) {
            Node curr = mq.remove();
            System.out.print(curr.data + " ");
            for (Node child : curr.children)
                cq.add(child);
            if (mq.size() == 0) {
                System.out.println();
                mq = cq;
                cq = new ArrayDeque<>();
            }
        }
    }

    // ! approach 2 => using dummy node/deliminator/-1 in one queue
    public static void levelOrderLinewise2(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        // dummy node
        Node dummy = new Node(-1);
        q.add(dummy);
        while (q.size() != 0) {
            Node curr = q.remove();
            if (curr.data == -1) {
                System.out.println();
                if (q.size() > 0)
                    q.add(dummy);
            } else {
                System.out.print(curr.data + " ");
                for (Node child : curr.children)
                    q.add(child);
            }
        }
    }

    // ! approaches 3 => some changes in the level order traversal(taking out size
    // of every level & looping only)
    public static void levelOrderLinewise3(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                Node curr = q.remove();
                System.out.print(curr.data + " ");
                for (Node child : curr.children)
                    q.add(child);
            }
            System.out.println();
        }
    }

    public static void levelOrderLinewise(Node node) {
        levelOrderLinewise1(node);
        levelOrderLinewise2(node);
        levelOrderLinewise3(node);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        Node root = constructATree(arr);
        displayTreeDFS(root);
        levelOrderLinewise(root);
    }
}
