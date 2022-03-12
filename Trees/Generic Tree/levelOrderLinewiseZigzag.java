import java.util.*;
import java.io.*;

public class levelOrderLinewiseZigzag {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node() {
            this.data = 0;
            this.children = new ArrayList<>();
        }

        Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    public static Node constructATree(int[] arr) {
        Node root = null;
        Stack<Node> stk = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1)
                stk.pop();
            else {
                Node temp = new Node(arr[i]);
                if (stk.size() == 0)
                    root = temp;
                else
                    stk.peek().children.add(temp);
                stk.push(temp);
            }
        }
        return root;
    }

    public static void displayTreeDFS(Node node) {
        System.out.print(node.data + " -> ");
        for (Node child : node.children)
            System.out.print(child.data + ", ");
        System.out.println();
        // faith
        for (Node child : node.children)
            displayTreeDFS(child);
    }

    // ! need 2 stacks - mainStack & childStack to print zigzag
    // ? in odd level - print tree left to right
    // ? in even level - print tree right to left
    public static void levelOrderZigzag(Node node) {
        Stack<Node> ms = new Stack<>();
        Stack<Node> cs = new Stack<>();
        int level = 1;
        ms.add(node);
        while (ms.size() != 0) {
            Node curr = ms.pop();
            System.out.print(curr.data + " ");
            // for odd - left to right print
            if (level % 2 != 0) {
                for (int i = 0; i < curr.children.size(); i++) {
                    cs.push(curr.children.get(i));
                }
            } // for even - right to left print
            else {
                for (int i = curr.children.size() - 1; i >= 0; i--) {
                    cs.push(curr.children.get(i));
                }
            }
            if (ms.size() == 0) {
                ms = cs;
                cs = new Stack<>();
                System.out.println();
                level++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        Node root = constructATree(arr);
        displayTreeDFS(root);
        levelOrderZigzag(root);
    }
}