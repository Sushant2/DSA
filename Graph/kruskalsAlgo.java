import java.util.*;
import java.io.*;

public class kruskalsAlgo {

    public static class Node {
        int u, v, weight;

        Node(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static class sortComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            return n1.weight - n2.weight;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        // using a linear DS not adjacency list to store graph
        ArrayList<Node> adj = new ArrayList<>();
        adj.add(new Node(0, 1, 2));
        adj.add(new Node(0, 3, 6));
        adj.add(new Node(1, 3, 8));
        adj.add(new Node(1, 2, 3));
        adj.add(new Node(1, 4, 5));
        adj.add(new Node(2, 4, 7));
        int minCcst = getMST(adj, n);
    }
}
