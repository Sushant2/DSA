import java.io.*;
import java.util.*;

public class detectCycleInDirectedGraph {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    public static boolean isCycle(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, int[] dfsVis) {
        vis[node] = 1;
        dfsVis[node] = 1;
        for (Integer it : adj.get(node)) {
            if (vis[it] == 0) {
                if (isCycle(it, adj, vis, dfsVis) == true)
                    return true;
            } else if (dfsVis[it] == 1)
                return true;
        }
        // if there's no adjacent nodes, & while returning, unmarked nodes in dfsVis
        // array
        dfsVis[node] = 0;
        return false;
    }

    public static boolean detectCycle(ArrayList<ArrayList<Integer>> adj, int V) {
        // extra dfsVis array to keeping track if nodes are visited in the current
        // movement, then only i can say that they did have a cycle
        int[] vis = new int[V + 1];
        int[] dfsVis = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            if (vis[i] == 0)
                if (isCycle(i, adj, vis, dfsVis) == true)
                    return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V + 1; i++)
            adj.add(new ArrayList<Integer>());
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 3);

        boolean ans = detectCycle(adj, V);
        System.out.println(ans);
    }
}
