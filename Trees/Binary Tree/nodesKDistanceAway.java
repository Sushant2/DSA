import java.io.*;
import java.util.*;

public class nodesKDistanceAway {
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

    public static ArrayList<Node> nodeToRoot(Node root, int data) {
        // -ve base case
        if (root == null)
            return new ArrayList<>();
        // +ve base case
        if (root.data == data) {
            ArrayList<Node> base = new ArrayList<>();
            base.add(root);
            return base;
        }
        ArrayList<Node> lans = nodeToRoot(root.left, data);
        if (lans.size() > 0) {
            lans.add(root);
            return lans;
        }
        ArrayList<Node> rans = nodeToRoot(root.right, data);
        if (rans.size() > 0) {
            rans.add(root);
            return rans;
        }
        return new ArrayList<>();
    }

    public static void printKFarAway(Node root, int data, int k) {
        if (root == null)
            return;
        // get node to root path in arrayList
        ArrayList<Node> n2r = nodeToRoot(root, data);
        for (int i = 0; i < n2r.size(); i++) {
            if (k >= 0) {
                Node blockage = (i == 0) ? null : n2r.get(i - 1);
                printKDowns(n2r.get(i), k);
                k--;
            }
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
        int data = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        printKFarAway(root, data, k);
    }
}
