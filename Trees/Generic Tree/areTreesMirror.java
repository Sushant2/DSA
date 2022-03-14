import java.util.*;
import java.io.*;

public class areTreesMirror {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public static Node construct(int[] arr) {
        Node root = null;
        Stack<Node> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1)
                stk.pop();
            else {
                Node curr = new Node();
                curr.data = arr[i];
                if (stk.size() == 0)
                    root = curr;
                else
                    stk.peek().children.add(curr);
                stk.push(curr);
            }
        }
        return root;
    }

    public static void display(Node node) {
        // corner case
        if (node == null)
            return;
        String str = node.data + " -> ";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        str += ".";
        System.out.println(str);
        // faith
        for (Node child : node.children) {
            display(child);
        }
    }

    // we can check are trees mirror or not by simple modification in code of
    // "areTreesSimilar"
    // in one tree, we'll traverse from left to right, & in second tree we'll
    // traverse from right to left, to the same work of checking children size of
    // nodes
    public static boolean areTreeMirror(Node root1, Node root2) {
        if (root1.children.size() != root2.children.size())
            return false;
        for (int i = 0; i < root1.children.size(); i++) {
            Node c1 = root1.children.get(i);
            Node c2 = root2.children.get(root2.children.size() - 1 - i);
            boolean ans = areTreeMirror(c1, c2);
            if (!ans)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values1 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values1[i]);
        }
        Node root1 = construct(arr);
        int m = Integer.parseInt(br.readLine());
        int[] brr = new int[m];
        String[] values2 = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            brr[i] = Integer.parseInt(values2[i]);
        }
        Node root2 = construct(brr);
        // boolean ans = areTreeSimilar(root1, root2);
        boolean ans = areTreeMirror(root1, root2);
        if (ans)
            System.out.println("Trees are mirror!");
        else
            System.out.println("Trees are not mirror!");
    }
}
