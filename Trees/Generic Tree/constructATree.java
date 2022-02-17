import java.io.*;
import java.util.*;

public class constructATree {
    // ! Constructing a Tree Node
    private static class Node {
        int data; // by default = 0
        ArrayList<Node> children; // by deafult = null
        // ? if we don't want to write constructor, write this
        // ArrayList<Node> children = new ArrayList<>();

        // constructor
        Node() {
            this.data = 0;
            this.children = new ArrayList<>(); // creating an empty arraylist in heap & giving reference to children
        }

        // constructor overloading
        Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    public static Node construct(int[] arr) {
        // creating root node to return
        Node root = null;
        Stack<Node> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                // end of child marker node
                stk.pop();
            } else {
                // creation of curr node
                // Node curr = new Node(arr[i]);
                Node curr = new Node();
                curr.data = arr[i];
                if (stk.size() == 0) {
                    // curr node is root node(root has no parent)
                    root = curr;
                } else {
                    // make curr node as a child of parent(stk.peek())
                    stk.peek().children.add(curr);
                }
                // push curr node in stack in preorder
                stk.push(curr);
            }
        }
        return root;
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
        // display(root);
    }
}
