import java.util.*;
import java.io.*;

public class detectCycleInDirectedGraphBFS {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    public static boolean detectCycle(ArrayList<ArrayList<Integer>> adj, int V) {
        // use reverse logic of find topological sort for graph
        int[] topo = new int[V];
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (Integer x : adj.get(i))
                indegree[x]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++)
            if (indegree[i] == 0)
                q.add(i);
        int cnt = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            cnt++;
            for (Integer it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0)
                    q.add(it);
            }
        }
        if (cnt == V)
            return false;
        return true;
    }

    public static void main(String[] args) throws Exception {
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= V + 1; i++)
            adj.add(new ArrayList<>());

        // addEdge(adj, 2, 3);
        // addEdge(adj, 3, 1);
        // addEdge(adj, 4, 0);
        // addEdge(adj, 4, 1);
        // addEdge(adj, 5, 0);
        // addEdge(adj, 5, 2);
        addEdge(adj, 5, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 1);
        addEdge(adj, 1, 4);
        addEdge(adj, 4, 0);
        addEdge(adj, 0, 5);
        boolean ans = detectCycle(adj, V);
        System.out.println(ans);
    }
}
