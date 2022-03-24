import java.io.*;
import java.util.*;

public class TCNodewithMaxSum {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    public static Node constructTree(int[] arr) {
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

    // 2 static vars - maxSum, maxSumNode
    static int maxSum = Integer.MIN_VALUE;
    static Node maxSumNode = null;

    public static int nodeWithMaxSum(Node root) {
        int sum = root.data;
        for (Node child : root.children)
            sum += nodeWithMaxSum(child);
        if (sum > maxSum) {
            maxSum = sum;
            maxSumNode = root;
        }
        return sum;
    }

    // w/o using static vars - using class pair object
    public static class Pair {
        int maxSum;
        Node maxSumNode;
        int sum;

        Pair(int sum) {
            this.sum = sum;
        }
    }

    public static Pair subTreeSum(Node root) {
        Pair ans = new Pair(root.data);
        for (Node child : root.children) {
            Pair temp = subTreeSum(child);
            ans.sum += temp.sum;
            if (temp.maxSum > ans.maxSum) {
                ans.maxSum = temp.maxSum;
                ans.maxSumNode = temp.maxSumNode;
            }
        }
        if (ans.sum > ans.maxSum) {
            ans.maxSum = ans.sum;
            ans.maxSumNode = root;
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }

        Node root = constructTree(arr);
        // nodeWithMaxSum(root);
        Pair ans = subTreeSum(root);
        System.out.println("Node with maximum sum: " + ans.maxSumNode.data + "@" + ans.maxSum);
    }
}
