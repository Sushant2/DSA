import java.util.*;
import java.io.*;

public class dfsTraversal {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> dfs) {
        dfs.add(node);
        vis[node] = true;
        for (Integer x : adj.get(node)) {
            if (vis[x] == false)
                dfs(x, vis, adj, dfs);
        }
    }

    // if a graph having only one component
    public static ArrayList<Integer> getDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] vis = new boolean[V + 1];
        dfs(1, vis, adj, dfs);
        return dfs;
    }

    public static void main(String[] args) throws Exception {
        int V = 7;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i < V + 1; i++)
            adj.add(new ArrayList<Integer>());

        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 2, 7);
        addEdge(adj, 3, 5);
        addEdge(adj, 5, 7);
        addEdge(adj, 4, 6);
        ArrayList<Integer> dfsTraversal = getDFS(V, adj);
        System.out.println(dfsTraversal);
    }
}
