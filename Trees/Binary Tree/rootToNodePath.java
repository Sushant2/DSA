import java.util.*;
import java.io.*;

public class rootToNodePath {
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

    public static void displayBTree(Node root) {
        if (root == null)
            return;
        if (root.left != null)
            System.out.print(root.left.data);
        else
            System.out.print(".");
        System.out.print(" <- " + root.data + " -> ");
        if (root.right != null)
            System.out.print(root.right.data);
        else
            System.out.print(".");

        System.out.println();
        // preorder
        displayBTree(root.left);
        // inorder
        displayBTree(root.right);
        // postorder
    }

    public static ArrayList<Integer> rootToNode(Node root, int data) {
        // -ve base case
        if (root == null)
            return new ArrayList<>();
        // +ve base case
        if (root.data == data) {
            ArrayList<Integer> base = new ArrayList<>();
            base.add(root.data);
            return base;
        }
        ArrayList<Integer> lans = rootToNode(root.left, data);
        if (lans.size() > 0) {
            lans.add(root.data);
            return lans;
        }
        ArrayList<Integer> rans = rootToNode(root.right, data);
        if (rans.size() > 0) {
            rans.add(root.data);
            return rans;
        }
        return new ArrayList<>();
    }

    // ! root to node path - passing arraylist as parameter - kind of backtracking -
    // ! jate hue add kr rhe hai, & aate hue hata rhe hai agar node nhi mili
    // ! to/false
    public static boolean rootToNode(Node root, int data, ArrayList<Integer> ans) {
        if (root == null)
            return false;
        if (root.data == data) {
            ans.add(root.data);
            return true;
        }
        ans.add(root.data);
        boolean left = rootToNode(root.left, data, ans);
        if (left == true)
            return true;
        boolean right = rootToNode(root.right, data, ans);
        if (right == true)
            return true;
        ans.remove(ans.size() - 1);
        return false;
    }

    // ! get all root to node paths of a node in a arraylist
    // ? time complexity - O(n*h) => O(n) for dfs, o(h) for copying/deep copy - if
    // ? in worst case every node is the resultant node - so we've to get root to
    // ? node path of every single node
    // ! we want every path so not returning true/false
    // ! deep copy - cos curr is a reference in heap - & changes will persist so in
    // the end curr becomes zero & in ress we'll add nothing so, we'll not get our
    // desired ans
    // TODO: DO DRY RUN FOR BETTER UNDERSTANDNG
    public static void rootToNodePaths(Node root, int data, ArrayList<Integer> curr,
            ArrayList<ArrayList<Integer>> res) {
        // -ve base case
        if (root == null)
            return;
        curr.add(root.data);
        // +ve base case
        if (root.data == data) {
            // deep copying not copying directly as = res.add(curr)
            ArrayList<Integer> temp = new ArrayList<>();
            for (Integer i : curr)
                temp.add(i);
            res.add(temp);
        }
        rootToNodePaths(root.left, data, curr, res);
        rootToNodePaths(root.right, data, curr, res);
        curr.remove(curr.size() - 1);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
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
        ArrayList<Integer> ans = rootToNode(root, data);
        System.out.println(ans.toString());
        ArrayList<Integer> path = new ArrayList<>();
        rootToNode(root, data, path);
        System.out.println(path.toString());
        ArrayList<Integer> path2 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        rootToNodePaths(root, data, path2, paths);
        System.out.println(paths.toString());
    }
}
