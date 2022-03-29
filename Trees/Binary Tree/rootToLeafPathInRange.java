import java.io.*;
import java.util.*;

public class rootToLeafPathInRange {
    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node constructBTree(Integer[] arr) {
        Node root = new Node(arr[0]);
        Stack<Pair> stk = new Stack<>();
        stk.push(new Pair(root, -1));
        int idx = 0;
        while (!stk.isEmpty()) {
            Pair par = stk.peek();
            if (par.state == -1) {
                // preorder
                idx++;
                if (arr[idx] != null) {
                    Node child = new Node(arr[idx]);
                    par.node.left = child;
                    stk.push(new Pair(child, -1));
                }
                par.state++;
            } else if (par.state == 0) {
                // inorder
                idx++;
                if (arr[idx] != null) {
                    Node child = new Node(arr[idx]);
                    par.node.right = child;
                    stk.push(new Pair(child, -1));
                }
                par.state++;
            } else
                stk.pop();
        }
        return root;
    }

    // ?1st variation of code
    public static void pathRootToLeafInRange(Node root, String path, int sum, int low, int high) {
        if (root == null) {
            return;
        }
        // leaves
        if (root.left == null && root.right == null) {
            // add root.data for leaf nodes
            path += root.data + " ";
            sum += root.data;
            if (sum >= low && sum <= high) {
                System.out.println(path);
                return;
            }
        }
        pathRootToLeafInRange(root.left, path + root.data + " ", sum + root.data, low, high);
        pathRootToLeafInRange(root.right, path + root.data + " ", sum + root.data, low, high);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // !we're creating array using non primitive data type/class cos, "Integer"
        // class can also store/hold null values
        Integer[] arr = new Integer[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            if (values[i].equals("n") == false)
                arr[i] = Integer.parseInt(values[i]);
            else
                arr[i] = null;
        }
        Node root = constructBTree(arr);
        int low = Integer.parseInt(br.readLine());
        int high = Integer.parseInt(br.readLine());

        pathRootToLeafInRange(root, "", 0, low, high);
    }
}
