import java.io.*;
import java.util.*;

public class diameterOfaTree {
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

    // return height ko, & calculate diameter
    static int maxDia = 0;

    public static int diameter(Node root) {
        if (root == null)
            return 0;
        // 2 maxs cos - distance b/w two leafs - so max height for first leaf in subtree
        // & height for 2nd max subtree
        int max1 = -1, max2 = -1;
        for (Node child : root.children) {
            // hc = height of child
            int hc = diameter(child);
            if (hc >= max1) {
                max2 = max1;
                max1 = hc;
            } else if (hc >= max2)
                max2 = hc;
        }
        int dia = max1 + max2 + 2;
        maxDia = Math.max(dia, maxDia);

        // sare children's height mese maxm height and khud ki 1 edge
        return (max1 + 1);

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
        diameter(root);
        System.out.println(maxDia);
    }
}
