import java.util.*;
import java.io.*;

public class detectBipartiteGraphBFS {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // for multiple components
    public static boolean checkBipartite1(ArrayList<ArrayList<Integer>> adj, int V) {
        int[] color = new int[V + 1];
        Arrays.fill(color, -1);
        for (int i = 1; i <= V; i++) {
            if (color[i] == -1) {
                if (!checkBiGraph(i, color, adj))
                    return false;
            }
        }
        return true;
    }

    // for single component
    public static boolean checkBipartite(ArrayList<ArrayList<Integer>> adj, int V) {
        int[] color = new int[V + 1];
        Arrays.fill(color, -1);
        if (!checkBiGraph(0, color, adj))
            return false;
        return true;
    }

    public static boolean checkBiGraph(int node, int[] color,
            ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        color[node] = 1;
        q.add(node);
        while (!q.isEmpty()) {
            int n = q.remove();
            for (Integer it : adj.get(n)) {
                if (color[it] == -1) {
                    color[it] = 1 - color[n];
                    q.add(it);
                }
                if (color[it] == color[n])
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int V = 8;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i < V + 1; i++)
            adj.add(new ArrayList<Integer>());

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 0);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 1);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 2);
        addEdge(adj, 4, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 4, 5);
        addEdge(adj, 5, 4);
        addEdge(adj, 4, 6);
        addEdge(adj, 6, 4);

        addEdge(adj, 6, 7);
        addEdge(adj, 7, 6);
        addEdge(adj, 1, 7);
        addEdge(adj, 7, 1);

        addEdge(adj, 1, 6);
        addEdge(adj, 6, 1);

        boolean ans = checkBipartite(adj, V);
        System.out.println(ans);
    }
}
