import java.io.*;
import java.util.*;

public class prePostTraversal {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public static Node construct(int[] arr) {
        Node root = null;
        Stack<Node> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                stk.pop();
            } else {
                Node curr = new Node();
                curr.data = arr[i];
                if (stk.size() == 0) {
                    root = curr;
                } else {
                    stk.peek().children.add(curr);
                }
                stk.push(curr);
            }
        }
        return root;
    }

    public static void traversal(Node node) {
        System.out.println("Node pre " + node.data);
        for (Node child : node.children) {
            System.out.println("Edge pre " + node.data + "--" + child.data);
            traversal(child);
            System.out.println("Edge post " + node.data + "--" + child.data);
        }
        System.out.println("Node post " + node.data);
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
        traversal(root);
    }
}
