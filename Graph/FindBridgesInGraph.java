import java.io.*;
import java.util.*;

public class FindBridgesInGraph {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    private static void dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, int timer, int[] vis, int[] tin,
            int[] low) {
        vis[node] = 1;
        tin[node] = low[node] = timer++;

        // iterate for it's adjacent nodes
        for (Integer it : adj.get(node)) {
            // it adjacent is parent, skip
            if (it == parent)
                continue;
            if (vis[it] == 0) {
                dfs(it, node, adj, timer, vis, tin, low);
                // update low whenever the dfs for the adj. node is complete
                low[node] = Math.min(low[node], low[it]);

                // we can remove edge if -
                if (low[it] > tin[node])
                    System.out.println(node + " - " + it);
            } else {
                low[node] = Math.min(low[node], tin[it]);
            }
        }
    }

    public static void printBridges(ArrayList<ArrayList<Integer>> adj, int V) {
        int[] vis = new int[V];
        int[] TIN = new int[V];
        int[] low = new int[V];
        int timer = 0;

        // start DFS traversal for unvisited nodes
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, -1, adj, timer, vis, TIN, low);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            adj.add(new ArrayList<>());
        }
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);

        printBridges(adj, V);
    }
}
