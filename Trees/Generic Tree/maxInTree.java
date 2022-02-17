import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;

public class maxInTree {
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

    public static int maxTree(Node node) {
        int max = node.data;
        for (Node child : node.children) {
            max = Math.max(max, maxTree(child));
        }
        return max;
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
        display(root);
        System.out.println("Max in tree: " + maxTree(root));
    }
}