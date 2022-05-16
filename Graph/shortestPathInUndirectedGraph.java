import java.io.*;
import java.util.*;

public class shortestPathInUndirectedGraph {
    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static int[] getPaths(ArrayList<ArrayList<Integer>> adj, int V, int src) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        dist[src] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            for (Integer n : adj.get(node)) {
                if (dist[node] + 1 < dist[n]) {
                    dist[n] = dist[node] + 1;
                    q.add(n);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int V = 9;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i < V + 1; i++)
            adj.add(new ArrayList<Integer>());

        for (int i = 0; i < V + 1; i++) {
            int u = scn.nextInt();
            int v = scn.nextInt();
            addEdge(adj, u, v);
        }

        int[] shortestPaths = getPaths(adj, V, 0);
        for (int i = 0; i < V; i++)
            System.out.print(shortestPaths[i] + " ");

    }
}
