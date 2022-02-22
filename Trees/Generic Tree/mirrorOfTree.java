import java.io.*;
import java.util.*;

public class mirrorOfTree {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public static Node construct(int[] arr) {
        // create a root node initialisation with null
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

    public static void display(Node node) {
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
    // ! while mirroring a genric tree, it does't matter we do our work/meeting
    // expec in pre or post order,the answer is gonna be same

    // !post order
    public static void mirror_post(Node node) {
        if (node == null)
            return;
        for (Node child : node.children) {
            mirror_post(child);
        }
        Collections.reverse(node.children);
    }

    // !pre order
    public static void mirror_pre(Node node) {
        if (node == null) {
            return;
        }
        Collections.reverse(node.children);
        for (Node child : node.children) {
            mirror_pre(child);
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
        System.out.println("Before mirror: ");
        display(root);
        mirror_pre(root);
        System.out.println("After mirror: ");
        display(root);
    }
}
