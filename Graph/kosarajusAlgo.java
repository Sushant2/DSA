import java.util.*;
import java.io.*;

public class kosarajusAlgo {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    private static void dfs(int node, Stack<Integer> stk, ArrayList<ArrayList<Integer>> adj, int[] vis) {
        vis[node] = 1;
        for (Integer it : adj.get(node)) {
            if (vis[it] == 0)
                dfs(it, stk, adj, vis);
        }
        stk.push(node);
    }

    public static void revDFS(int node, ArrayList<ArrayList<Integer>> transpose, int[] vis) {
        vis[node] = 1;
        System.out.print(node + " ");
        for (Integer it : transpose.get(node)) {
            if (vis[it] == 0)
                revDFS(it, transpose, vis);
        }
    }

    public static void kosarajuAlgo(ArrayList<ArrayList<Integer>> adj, int V) {
        // step 1 : find topo sort
        int[] vis = new int[V];
        Stack<Integer> topo = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0)
                dfs(i, topo, adj, vis);
        }

        // step 2 :get transpose
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < V; i++)
            transpose.add(new ArrayList<>());
        for (int i = 0; i < V; i++) {
            // making the visited array unvisited cos we did that while finding topo
            vis[i] = 0;
            for (Integer it : adj.get(i))
                transpose.get(it).add(i);
        }

        // step 3 : do DFS acc. to finishing time
        while (!topo.isEmpty()) {
            int node = topo.pop();
            if (vis[node] == 0) {
                System.out.println("SSC: ");
                revDFS(node, transpose, vis);
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V + 1; i++)
            adj.add(new ArrayList<>());
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);
        addEdge(adj, 1, 3);
        addEdge(adj, 3, 4);

        kosarajuAlgo(adj, V);
    }
}
