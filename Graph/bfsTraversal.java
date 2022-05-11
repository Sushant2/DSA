import java.util.*;
import java.io.*;

public class bfsTraversal {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static ArrayList<Integer> getBFS(ArrayList<ArrayList<Integer>> adj, int s, int V) {
        // BFS Traversal
        // if graph is having one component only
        ArrayList<Integer> bfs = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        boolean visited[] = new boolean[V + 1];
        q.add(s);
        visited[s] = true;
        while (!q.isEmpty()) {
            Integer node = q.remove();
            bfs.add(node);

            for (Integer x : adj.get(node)) {
                if (visited[x] == false) {
                    q.add(x);
                    visited[x] = true;
                }
            }
        }

        return bfs;
    }

    public static void main(String[] args) throws Exception {
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<Integer>());

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 3);

        ArrayList<Integer> bfsTraversal = getBFS(adj, 2, V);
        System.out.println(bfsTraversal);

    }
}