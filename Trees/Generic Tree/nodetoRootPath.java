import java.io.*;
import java.util.*;

public class nodetoRootPath {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node() {
        }

        Node(int data) {
            this.data = data;
        }
    }

    public static Node construct(int[] arr) {
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

    // time comp - O(n)
    public static ArrayList<Integer> nodeToRoot(Node node, int data) {
        // +ve base case - find in pre & return path in postorder
        if (node.data == data) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(node.data);
            return path;
        }
        for (Node child : node.children) {
            ArrayList<Integer> temp = nodeToRoot(child, data);
            if (temp.size() > 0) {
                temp.add(node.data);
                return temp;
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(values[i]);
        Node root = construct(arr);
        // displayTree(root);
        int data = Integer.parseInt(br.readLine());
        ArrayList<Integer> n2rp = nodeToRoot(root, data);
        System.out.println(n2rp.toString());
    }
}
