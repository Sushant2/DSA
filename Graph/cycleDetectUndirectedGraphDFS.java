import java.util.*;
import java.io.*;

public class cycleDetectUndirectedGraphDFS {
    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static boolean checkCycleDFS(int node, int parent, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        for (Integer it : adj.get(node)) {
            if (vis[it] == false) {
                if (checkCycleDFS(it, node, vis, adj) == true)
                    return true;
            } else if (it != parent)
                return true;
        }
        return false;
    }

    public static boolean isCycle(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] vis = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            if (vis[i] == false) {
                if (checkCycleDFS(i, -1, vis, adj))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        int V = 7;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i < V + 1; i++)
            adj.add(new ArrayList<Integer>());

        addEdge(adj, 1, 0);
        addEdge(adj, 0, 2);
        addEdge(adj, 2, 1);
        addEdge(adj, 0, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 4, 6);
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);

        boolean ans = isCycle(adj, V);
        System.out.println(ans);
    }
}