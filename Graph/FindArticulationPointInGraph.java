import java.util.*;
import java.io.*;

public class FindArticulationPointInGraph {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    private static void dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, int[] vis, int[] TIN, int[] low,
            ArrayList<Integer> ACPoints, int timer) {
        vis[node] = 1;
        TIN[node] = low[node] = timer++;
        int child = 0; // for counting multiple individual children

        for (Integer it : adj.get(node)) {
            if (it == parent)
                continue;
            if (vis[it] == 0) {
                dfs(it, node, adj, vis, TIN, low, ACPoints, timer);
                low[node] = Math.min(low[node], low[it]);

                if (low[it] >= TIN[node] && parent != -1)
                    ACPoints.add(node);
                child++;
            } else {
                low[node] = Math.min(low[node], TIN[it]);
            }
        }
        if (child > 1 && parent == -1)
            ACPoints.add(node);
    }

    public static void getArticulationPoint(ArrayList<ArrayList<Integer>> adj, int V) {
        int[] vis = new int[V];
        int[] TIN = new int[V];
        int[] low = new int[V];

        ArrayList<Integer> articulationPoints = new ArrayList<>();
        int timer = 0;
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, -1, adj, vis, TIN, low, articulationPoints, timer);
            }
        }

        for (Integer x : articulationPoints)
            System.out.print(x + " ");
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

        getArticulationPoint(adj, V);
    }
}
