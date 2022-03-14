import java.io.*;
import java.util.*;

public class linearizeATree {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    public static Node construct(int[] arr) {
        // create root node - initaize with null
        Node root = null;
        // create stack to push pop node
        Stack<Node> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1)
                stk.pop();
            else {
                // create a curr node with data as arr[i]
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

    public static void display(Node node) {
        // edge case
        if (node == null)
            return;
        System.out.print(node.data + " -> ");
        for (Node child : node.children) {
            System.out.print(child.data + ", ");
        }
        System.out.println(".");
        // faith
        for (Node child : node.children) {
            display(child);
        }
    }

    // ! brute force - O(n^2)
    public static Node getTail(Node node) {
        while (node != null && node.children.size() > 0) {
            node = node.children.get(0);
        }
        return node;
    }

    public static void linearize1(Node node) {
        if (node == null)
            return;
        for (Node child : node.children) {
            linearize1(child);
        }
        for (int i = node.children.size() - 1; i > 0; i--) {
            Node rightChild = node.children.get(i);
            Node leftChildTail = getTail(node.children.get(i - 1));
            leftChildTail.children.add(rightChild);
            node.children.remove(i);
        }
    }

    // ! optimised approach - time comp - O(n), using arraylist to store & return
    // tails of childs
    public static Node linearize2(Node node) {
        if (node == null)
            return null;
        ArrayList<Node> tails = new ArrayList<>();
        for (Node child : node.children) {
            tails.add(linearize2(child));
        }
        for (int i = node.children.size() - 1; i > 0; i--) {
            Node rightChild = node.children.get(i);
            Node leftChildTail = tails.get(i - 1);
            leftChildTail.children.add(rightChild);
            node.children.remove(i);
        }
        if (tails.size() == 0)
            return node;
        return tails.get(tails.size() - 1);
    }

    // ! optimised approach - w/o using arraylist to store tails, as we know the
    // last tail is the tail of last subtree, & gonna be tail for second last
    // subtree and so on...
    public static Node linearize3(Node node) {
        // edge case - if node is leaf node then, that node itself acts as a leaf
        // node/tail node
        if (node.children.size() == 0) {
            return node;
        }
        // pahle hi last tree tail node mangwalo - kyoki yahi tail sabke lie tail node
        // banne wala hai jese jese hum peeche se solve krna shurur krenge & sath sath
        // linearize bhi call kr rhe h
        Node lastTreeTail = linearize3(node.children.get(node.children.size() - 1));
        while (node.children.size() > 1) {
            Node lastTreeHead = node.children.remove(node.children.size() - 1);
            Node secondLastTreeTail = linearize2(node.children.get(node.children.size() - 1));
            secondLastTreeTail.children.add(lastTreeHead);
        }
        return lastTreeTail;
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
        System.out.println("Tree before linearization: ");
        display(root);
        linearize3(root);
        System.out.println("Tree after linearization: ");
        display(root);

    }
}
