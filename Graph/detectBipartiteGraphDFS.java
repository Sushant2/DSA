import java.io.*;
import java.util.*;

public class detectBipartiteGraphDFS {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static boolean bipartiteDFS(int node, int[] colors, ArrayList<ArrayList<Integer>> adj) {
        if (colors[node] == -1)
            colors[node] = 1;
        for (Integer it : adj.get(node)) {
            if (colors[it] == -1) {
                colors[it] = 1 - colors[node];
                if (!bipartiteDFS(it, colors, adj))
                    return false;
            } else if (colors[it] == colors[node])
                return false;
        }
        return true;
    }

    public static boolean checkBipartite(ArrayList<ArrayList<Integer>> adj, int V) {
        int[] colors = new int[V + 1];
        Arrays.fill(colors, -1);
        for (int i = 1; i <= V; i++) {
            if (colors[i] == -1) {
                if (!bipartiteDFS(i, colors, adj))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i < V + 1; i++)
            adj.add(new ArrayList<Integer>());

        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 4, 5);
        // addEdge(adj, 5, 6);
        addEdge(adj, 5, 1);

        boolean ans = checkBipartite(adj, V);
        System.out.println(ans);
    }
}
