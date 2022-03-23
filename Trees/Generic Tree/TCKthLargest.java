import java.io.*;
import java.util.*;

public class TCKthLargest {

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

    // 2 static vars - kLargest, floor
    //! time complexity - O(k*n) = O(n^2)
    static int Klarge = Integer.MAX_VALUE;
    static int floor = Integer.MIN_VALUE;

    public static void findFloor(Node root, int data) {
        if (root.data < data) {
            floor = Math.max(root.data, floor);
        }
        for (Node child : root.children) {
            findFloor(child, data);
        }
    }

    public static int Klargest(Node root, int k) {
        for (int i = 1; i <= k; i++) {
            floor = Integer.MIN_VALUE;
            findFloor(root, Klarge);
            Klarge = floor;
        }
        return Klarge;
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
        int k = Integer.parseInt(br.readLine());
        int ans = Klargest(root, k);
        System.out.println("Kth largest: "+ ans);
    }
}
