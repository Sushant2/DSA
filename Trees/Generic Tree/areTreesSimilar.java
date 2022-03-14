import java.util.*;
import java.io.*;

public class areTreesSimilar {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public static Node construct(int[] arr) {
        Node root = null;
        Stack<Node> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1)
                stk.pop();
            else {
                Node curr = new Node();
                curr.data = arr[i];
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
        String str = node.data + " -> ";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        str += ".";
        System.out.println(str);
        // faith
        for (Node child : node.children) {
            display(child);
        }
    }

    // ! O(n)
    // we're assuming from starting that they both are of same size, so only looping
    // of roo1.children & aslo checking first condition above
    public static boolean areTreeSimilar(Node root1, Node root2) {
        if (root1.children.size() != root2.children.size())
            return false;
        for (int i = 0; i < root1.children.size(); i++) {
            Node c1 = root1.children.get(i);
            Node c2 = root2.children.get(i);
            boolean ans = areTreeSimilar(c1, c2);
            if (!ans)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values1 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values1[i]);
        }
        Node root1 = construct(arr);
        int m = Integer.parseInt(br.readLine());
        int[] brr = new int[m];
        String[] values2 = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            brr[i] = Integer.parseInt(values2[i]);
        }
        Node root2 = construct(brr);
        boolean ans = areTreeSimilar(root1, root2);
        if (ans)
            System.out.println("Trees are similar!");
        else
            System.out.println("Trees are not similar!");
    }
}

// 20
// 1 2 5 -1 6 -1 -1 3 7 -1 8 -1 -1 4 9 -1 10 -1 -1 -1
// 20
// 10 20 50 -1 60 -1 -1 30 70 -1 80 -1 -1 40 90 -1 100 -1 -1 -1