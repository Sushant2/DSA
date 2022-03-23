//TC - travel and change strategy

import java.io.*;
import java.util.*;

public class TCceilFloor {

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

    // 2 static vars - ceil & floor
    static int ceil = Integer.MAX_VALUE;
    static int floor = Integer.MIN_VALUE;

    public static void ceilFloor(Node node, int data) {
        if (node.data > data) {
            ceil = Math.min(ceil, node.data);
        }
        if (node.data < data) {
            floor = Math.max(floor, node.data);
        }
        for (Node child : node.children)
            ceilFloor(child, data);
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
        int data = Integer.parseInt(br.readLine());
        ceilFloor(root, data);
        System.out.println("Ceil is: " + ceil);
        System.out.println("Floor is: " + floor);
    }
}
