import java.util.*;
import java.io.*;

// "tilt" of node - mat.abs(left tree sum - right tree sum)
// "tilt" of whole tree - sum of tilts of all nodes
public class tiltOfBinaryTree {

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

    // ! expectations - hum kisi node par rakhenge ki, hame uska tilt niaklana h
    // ! faith - left se is poore tree ka sum miljaye(left sum tree), right se is
    // ! poore ka sum miljaye(right sum tree)
    // ! the our tilt = abs(leftsum - rightsum)

    // ? "OBSERVE" that - return hora h sum, kyoki final ans to sum return
    // ? krenege sare tilts ka & calculate hora h tilt

    // ! So, we use TRAVEL AND CHANGE

    // TODO return sum, calculate tilt

    static int tiltSum = 0;

    public static int tilt(Node root) {
        if (root == null)
            return 0;
        int lSum = tilt(root.left);
        int rSum = tilt(root.right);
        int abs = Math.abs(lSum - rSum);

        tiltSum += abs;
        return root.data + lSum + rSum;
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
        tilt(root);
        System.out.println(tiltSum);
    }
}
