import java.util.*;
import java.io.*;

public class cycleDetectUndirectedGraphBFS {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // Queue store pair : node & prevNode
    public static class Pair {
        int first, second;
        // first is node, second is prevNode or parent of node

        public Pair(int node, int prev) {
            this.first = node;
            this.second = prev;
        }
    }

    public static boolean checkCycleBFS(int s, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {
        vis[s] = true;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(s, -1));
        while (!q.isEmpty()) {
            int N = q.peek().first;
            int prevN = q.peek().second;
            q.remove();
            for (Integer it : adj.get(N)) {
                if (vis[it] == false) {
                    q.add(new Pair(it, N));
                    vis[it] = true;
                } else if (it != prevN)
                    return true;
            }
        }
        return false;
    }

    public static boolean isCycle(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] vis = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            if (vis[i] == false)
                if (checkCycleBFS(i, vis, adj))
                    return true;
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