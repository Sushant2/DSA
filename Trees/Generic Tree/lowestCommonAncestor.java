import java.io.*;
import java.util.*;

public class lowestCommonAncestor {
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

    // ? solve it using - node to root path
    // time comp - O(n) && space - O(n)
    public static int LCA(Node root, int d1, int d2) {
        if (root == null)
            return -1;
        ArrayList<Integer> path1 = nodeToRoot(root, d1);
        ArrayList<Integer> path2 = nodeToRoot(root, d2);

        // return lca - break when we got first uncommon ele while traversing from back
        // in both list
        int lca = -1;
        int i = path1.size() - 1;
        int j = path2.size() - 1;
        while (i >= 0 && j >= 0) {
            if (path1.get(i) != path2.get(j))
                break;
            lca = path1.get(i);
            i--;
            j--;
        }
        return lca;
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
        int d1 = Integer.parseInt(br.readLine());
        int d2 = Integer.parseInt(br.readLine());
        System.out.println(LCA(root, d1, d2));
    }
}
