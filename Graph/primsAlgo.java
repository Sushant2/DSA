import java.util.*;
import java.io.*;

//prims algo is used to find minimum spanning tree of a graph - undirected weighted graph
public class primsAlgo {

    public static class Pair {
        int dest;
        int weight;

        Pair(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void addEdgeWeight(ArrayList<ArrayList<Pair>> adj, int u, int v, int w) {
        adj.get(u).add(new Pair(v, w));
        adj.get(v).add(new Pair(u, w));
    }

    // BRUTE FORCE IMPLEM... - time complexity - O(V^2) , space complexity - O(V)
    public static int[] getMST(ArrayList<ArrayList<Pair>> adj, int V) {
        int[] key = new int[V + 1]; // holds weight of MST(initialized to INT_MAX except index 0 which is set with
                                    // value 0)
        boolean[] MST = new boolean[V + 1]; // boolean array which indicates whether this node is a part of MST or not
                                            // (initialized to false)
        int[] parent = new int[V + 1]; // parent of a particular node in the MST(initialized to -1)
        for (int i = 1; i < V + 1; i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        key[1] = 0;

        // traverse for all n-1 edges as MST will have n nodes & n-1 edges
        for (int i = 0; i < V - 1; i++) {
            // traverse on the key array & find minimum & who is also not part of MST
            int mini = Integer.MAX_VALUE, u = 0; // u is index of that mini node
            for (int j = 0; j < V; j++) {
                if (MST[j] == false && key[j] < mini) {
                    mini = key[j];
                    u = j;
                }
            }
            // after picking that mini node, mark true in MST, that is is now a part of MST
            MST[u] = true;
            // now traverse all adjacent nodes of mini node
            for (Pair it : adj.get(u)) {
                if (MST[it.dest] == false && it.weight < key[it.dest]) {
                    parent[it.dest] = u;
                    key[it.dest] = it.weight;
                }
            }
        }
        return parent;
    }

    public static void main(String[] args) throws Exception {
        int V = 6;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<ArrayList<Pair>>();
        for (int i = 0; i < V + 1; i++)
            adj.add(new ArrayList<>());

        addEdgeWeight(adj, 4, 5, 9);
        addEdgeWeight(adj, 3, 4, 5);
        addEdgeWeight(adj, 6, 3, 8);
        addEdgeWeight(adj, 6, 2, 7);
        addEdgeWeight(adj, 2, 1, 2);
        addEdgeWeight(adj, 1, 5, 4);
        addEdgeWeight(adj, 1, 4, 1);
        addEdgeWeight(adj, 2, 4, 3);
        addEdgeWeight(adj, 2, 3, 3);
        int[] parent = getMST(adj, V);
        for (int i = 2; i < parent.length; i++)
            System.out.println(parent[i] + "-" + i);
    }
}
