import java.io.*;
import java.util.*;

//Leetcode - 653

public class targetSumPairInBST {

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node construct(int[] arr, int low, int high) {
        // base case
        if (low > high)
            return null;
        int mid = (low + high) / 2;
        Node root = new Node(arr[mid]);
        root.left = construct(arr, low, mid - 1);
        root.right = construct(arr, mid + 1, high);
        return root;
    }

    public static void display(Node root) {
        if (root == null)
            return;
        display(root.left);
        System.out.print(root.data + " ");
        display(root.right);
    }

    // !first approach : Inorder & find
    // time comp : O(n*h) + space comp : O(h)
    public static boolean find(Node root, int data) {
        if (root == null)
            return false;
        if (root.data > data)
            return find(root.left, data);
        else if (root.data < data)
            return find(root.right, data);
        else
            return true;
    }

    public static void targetSum(Node curr, int sum, Node root) {
        if (curr == null)
            return;
        targetSum(curr.left, sum, root);
        // inorder
        // stop when data > sum/2
        if (curr.data >= (sum / 2))
            return;
        int data = sum - curr.data;
        // ! NOTICE :we're passing root to find, not curr node
        if (find(root, data))
            System.out.println(curr.data + " " + data);
        targetSum(curr.right, sum, root);
    }

    // ! 2nd approach - Easiest apprach - Store nodes in array & use 2 pointers from
    // ! start & end of array
    // time comp - O(n) & space comp - o(n)
    public static void inorder(Node root, ArrayList<Integer> arr) {
        if (root == null)
            return;
        inorder(root.left, arr);
        arr.add(root.data);
        inorder(root.right, arr);
    }

    public static void targetSum2(Node root, int target) {
        ArrayList<Integer> arr = new ArrayList<>();
        inorder(root, arr);
        // 2 pointers on arraylist
        int left = 0, right = arr.size() - 1;
        while (left < right) {
            int sum = arr.get(left) + arr.get(right);
            if (sum == target) {
                System.out.println(arr.get(left) + " " + arr.get(right));
                left++;
                right--;
            } else if (sum < target)
                left++;
            else
                right--;
        }
    }

    // ! 3rd approach - Effiecient Apprach - Inorder traversal + Reverse Inorder
    // ! Traversal + 2 pointers
    // we can't use recursion on inorder/reverse inorder cos, we can't hold nodes to
    // calclute sum of nodes

    // ? so we'll use iterative inorder + iterative reverse inorder
    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void targetSum3(Node root, int target) {
        Stack<Pair> inorder = new Stack<>();
        inorder.push(new Pair(root, -1));
        Stack<Pair> reverseInorder = new Stack<>();
        reverseInorder.push(new Pair(root, -1));

        int left = iterativeInorder(inorder);
        int right = iterativeReverseInorder(reverseInorder);
        while (left < right) {
            if (left + right == target) {
                System.out.println(left + " " + right);
                left = iterativeInorder(inorder);
                right = iterativeReverseInorder(reverseInorder);
            } else if (left + right < target)
                left = iterativeInorder(inorder);
            else
                right = iterativeReverseInorder(reverseInorder);
        }
    }

    public static int iterativeInorder(Stack<Pair> inorder) {
        while (!inorder.isEmpty()) {
            Pair par = inorder.peek();
            if (par.state == -1) {
                // preorder
                if (par.node.left != null)
                    inorder.push(new Pair(par.node.left, -1));
                par.state++;
            } else if (par.state == 0) {
                // inorder
                int val = par.node.data;
                if (par.node.right != null)
                    inorder.push(new Pair(par.node.right, -1));
                par.state++;
                return val;
            } else if (par.state == 1)
                // postorder
                inorder.pop();
        }
        return -1;
    }

    public static int iterativeReverseInorder(Stack<Pair> reverseInorder) {
        while (!reverseInorder.isEmpty()) {
            Pair par = reverseInorder.peek();
            if (par.state == -1) {
                // reverse preorder
                if (par.node.right != null)
                    reverseInorder.push(new Pair(par.node.right, -1));
                par.state++;
            } else if (par.state == 0) {
                // reverse inorder
                int val = par.node.data;
                if (par.node.left != null)
                    reverseInorder.push(new Pair(par.node.left, -1));
                par.state++;
                return val;
            } else if (par.state == 1)
                // reverse postorder
                reverseInorder.pop();
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String values[] = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        Node root = construct(arr, 0, n - 1);
        int target = Integer.parseInt(br.readLine());
        // display(root);
        // targetSum(root, target, root);
        // targetSum2(root, target);
        targetSum3(root, target);

    }
}
