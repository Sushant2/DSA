import java.util.*;
import java.io.*;

//!TOPOLOGICAL SORT ONLY WORKS ON DAG(DIRECTED ACYCLIC GRPAH)
public class topologicalSortDFS {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    public static void sort(ArrayList<ArrayList<Integer>> adj, boolean[] vis, Stack<Integer> stk, int node) {
        vis[node] = true;
        for (Integer it : adj.get(node)) {
            if (vis[it] == false)
                sort(adj, vis, stk, it);
        }
        stk.push(node);
    }

    public static int[] getTopoSort(ArrayList<ArrayList<Integer>> adj, int V) {
        Stack<Integer> stk = new Stack<>();
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (vis[i] == false)
                sort(adj, vis, stk, i);
        }
        int idx = 0;
        int[] topo = new int[V];
        while (!stk.isEmpty()) {
            topo[idx++] = stk.pop();
        }
        return topo;
    }

    public static void main(String[] args) throws Exception {
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= V + 1; i++)
            adj.add(new ArrayList<>());
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 1);
        addEdge(adj, 4, 0);
        addEdge(adj, 4, 1);
        addEdge(adj, 5, 0);
        addEdge(adj, 5, 2);
        int[] topoSort = getTopoSort(adj, V);
        for (int x : topoSort)
            System.out.print(x + " ");
    }
}
