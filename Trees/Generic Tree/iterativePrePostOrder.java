import java.io.*;
import java.util.*;

class Pair {
    Node node;
    int state;
}

public class iterativePrePostOrder {

    private static class Node {
        int data;
        Stack<Node> children = new Stack<>();

        Node() {
        }

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(values[i]);
        Node root = constructTree(arr);
        iterPrePost(root);
    }
}
