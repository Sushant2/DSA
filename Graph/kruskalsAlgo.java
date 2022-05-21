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

    public static int findPar(int node, int[] parent) {
        if (parent[node] == node)
            return node;
        return parent[node] = findPar(parent[node], parent);
    }

    public static void union(int u, int v, int[] parent, int[] rank) {
        u = findPar(u, parent);
        v = findPar(v, parent);
        if (rank[u] < rank[v])
            parent[u] = v;
        else if (rank[u] > rank[v])
            parent[v] = u;
        else {
            parent[v] = u;
            rank[u]++;
        }
    }

    public static int getMST(ArrayList<Node> adj, int N) {
        Collections.sort(adj, new sortComparator());
        int[] parent = new int[N];
        int[] rank = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;
        int costMST = 0;
        ArrayList<Node> mst = new ArrayList<>();
        // traversing every edge
        for (Node it : adj) {
            if (findPar(it.u, parent) != findPar(it.v, parent)) {
                costMST += it.weight;
                mst.add(it);
                union(it.u, it.v, parent, rank);
            }
        }
        return costMST;
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
        int minCost = getMST(adj, n);
        System.out.println(minCost);
    }
}
