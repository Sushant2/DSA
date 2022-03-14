import java.io.*;
import java.util.*;

public class findInTree {

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

    // find an element using DFS - DFS is simple & no need to take any extra space,
    // like queue in BFS
    // ! O(n)
    public static boolean findDFS(Node node, int x) {
        // subtrees mein check krenge, and kisi bhi tree ne bhi true return krdia, to
        // wahi se hi ans return krdenge
        if (node.data == x) {
            return true;
        }
        for (Node child : node.children) {
            boolean ans = findDFS(child, x);
            if (ans)
                return true;
        }
        return false;
    }

    // we can also use "OR gate operator"
    public static boolean findDFSorGate(Node node, int x) {
        // subtrees mein check krenge, and kisi bhi tree ne bhi true return krdia, to
        // wahi se hi ans return krdenge
        if (node.data == x) {
            return true;
        }
        boolean ans = false;
        for (Node child : node.children) {
            ans |= findDFS(child, x);
            if (ans)
                return true;
        }
        return false;
    }

    // using BFS
    // ! O(n)
    public static boolean findBFS(Node node, int x) {
        // create a queue
        Queue<Node> q = new ArrayDeque<>();
        // add root new node
        q.add(node);
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                Node curr = q.remove();
                if (curr.data == x)
                    return true;
                for (Node child : node.children)
                    q.add(child);
            }
        }
        return false;
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
        int ele = Integer.parseInt(br.readLine());
        display(root);
        if (findDFSorGate(root, ele))
            System.out.println("Element found!");
        else
            System.out.println("Element not found!");
    }
}
