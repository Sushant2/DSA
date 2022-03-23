//TC - Travel and Change Strategy

import java.util.*;
import java.io.*;

public class TCpredecessorAndSuccessor {
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

    // 3 static variables - predecessor, successor, state
    static int pred = -1;
    static int succ = -1;
    static int state = -1;

    public static void predAndSucc(Node root, int data) {
        if (state == -1) {
            if (root.data == data)
                state++;
            else
                pred = root.data;
        } else if (state == 0) {
            succ = root.data;
            state++;
            return;
        }
        for (Node child : root.children) {
            if (state == 1)
                return;
            predAndSucc(child, data);
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
        int data = Integer.parseInt(br.readLine());
        predAndSucc(root, data);
        System.out.println("Predecessor: " + pred);
        System.out.println("Suceessor: " + succ);
    }
}
