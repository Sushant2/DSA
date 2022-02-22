import java.io.*;
import java.util.*;

public class removeLeafNodes {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    public static Node construct(int[] arr) {
        // create a root node initialisation with null
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

    public static void display(Node node) {
        // corner case
        if (node == null)
            return;
        System.out.print(node.data + " -> ");
        for (Node child : node.children) {
            System.out.print(child.data + ", ");
        }
        System.out.println(".");
        // faith
        for (Node child : node.children) {
            display(child);
        }
    }

    // ! we can't remove leaves in post order as - if will delete all those nodes
    // also, which were nonleaf at first or it'll also delete non leaf nodes

    // ? So only do in preorder
    // !using loop in reverse - will not skip any node while deleting
    public static void removeLeafs1(Node node) {
        if (node == null)
            return;
        // we are deleting leaf nodes in preorder
        for (int i = node.children.size() - 1; i >= 0; i--) {
            Node child = node.children.get(i);
            if (child.children.size() == 0) {
                // my child is leaf node
                node.children.remove(i);
            }
            // remove that child node from my children arraylist
            // we have removed the edge linking b/w the leaf node & it's parent
        }
        for (Node child : node.children) {
            removeLeafs1(child);
        }
    }

    // ! using loop in straight manner - but will skip nodes after removing - so to
    // not to skip, we'll do "i--" when any nodes gets removed
    public static void removeLeafs2(Node node) {
        if (node == null)
            return;
        // we are deleting leaf nodes in preorder
        for (int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
            if (child.children.size() == 0) {
                // my child is leaf node
                node.children.remove(i);
                i--;
            }
            // remove that child node from my children arraylist
            // we have removed the edge linking b/w the leaf node & it's parent
        }
        for (Node child : node.children) {
            removeLeafs2(child);
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
        Node root = construct(arr);
        System.out.println("Original Tree: ");
        display(root);
        removeLeafs2(root);
        System.out.println("Tree after removing leaf nodes: ");
        display(root);
    }
}
