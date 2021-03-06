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
        // null par to jaa hi rhe hai, jab ek hi child hoga to use opposite child pr
        // null hoga

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

    // ?2nd variation of code
    public static void pathRootToLeafInRange2(Node root, String path, int sum, int low, int high) {
        // null par to jaa hi rhe hai, jab ek hi child hoga to use opposite child pr
        // null hoga
        if (root == null) {
            return;
        }
        path += root.data + " ";
        sum += root.data;
        // leaves
        if (root.left == null && root.right == null) {
            if (sum >= low && sum <= high) {
                System.out.println(path);
                return;
            }
        }
        pathRootToLeafInRange(root.left, path, sum, low, high);
        pathRootToLeafInRange(root.right, path, sum, low, high);
    }

    // ! 3rd variation of code - is using string builder instead of string, so we've
    // !to backtrack in that case - removing added nodes
    public static void pathRootToLeafInRange3(Node node, StringBuilder path, int sum, int low, int high) {
        if (node == null) {
            return;
        }
        // node k pre mein add kr rhe hai
        sum += node.data;
        path.append(node.data);
        path.append(" ");
        if (node.left == null && node.right == null) {
            if (sum >= low && sum <= high) {
                System.out.println(path);
            }
            path.deleteCharAt(path.length() - 1);
            for (int i = path.length()-1; i >= 0 && path.charAt(i) != ' '; i--) {
                path.deleteCharAt(i);
            }
            return;
        }
        pathRootToLeafInRange3(node.left, path, sum, low, high);
        pathRootToLeafInRange3(node.right, path, sum, low, high);
        // node ke post mein remove kr rhe hai
        path.deleteCharAt(path.length() - 1);
        for (int i = path.length() - 1; i >= 0 && path.charAt(i) != ' '; i--) {
            path.deleteCharAt(i);
        }

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

        // pathRootToLeafInRange(root, "", 0, low, high);
        // pathRootToLeafInRange2(root, "", 0, low, high);
        StringBuilder sb = new StringBuilder("");
        pathRootToLeafInRange3(root, sb, 0, low, high);
    }
}
