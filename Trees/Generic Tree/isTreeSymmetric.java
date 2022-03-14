import java.io.*;
import java.util.*;

public class isTreeSymmetric {
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

    // we can do this by little modification in code of "are tree mirror"
    // as we'll pass the same node twice in the code of "are tree similar" cos,
    // symmtric means, one side is opposite of others(or looks same like mirror)
    public static boolean areTreeMirror(Node node1, Node node2) {
        if (node1.children.size() != node2.children.size())
            return false;
        for (int i = 0; i < node1.children.size(); i++) {
            Node c1 = node1.children.get(i);
            Node c2 = node2.children.get(node2.children.size() - 1 - i);
            boolean ans = areTreeMirror(c1, c2);
            if (!ans)
                return false;
        }
        return true;
    }

    public static boolean isSymmetric(Node node) {
        return areTreeMirror(node, node);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        Node root = construct(arr);
        boolean ans = isSymmetric(root);
        if (ans)
            System.out.println("Tree is symmetric!");
        else
            System.out.println("Tree is not symmetric!");
    }
}
