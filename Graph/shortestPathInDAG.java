import java.util.*;
import java.io.*;

public class shortestPathInDAG {
    public static void addEdgeWeight(ArrayList<ArrayList<Pair>> adj, int u, int v, int w) {
        adj.get(u).add(new Pair(v, w));
    }

    public static class Pair {
        int dest;
        int weight;

        Pair(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void getTOPO(ArrayList<ArrayList<Pair>> adj, Stack<Integer> stk, int node, boolean[] vis) {
        vis[node] = true;
        for (Pair it : adj.get(node)) {
            if (vis[it.dest] == false)
                getTOPO(adj, stk, it.dest, vis);
        }
        stk.push(node);
    }

    public static int[] getShortestPaths(ArrayList<ArrayList<Pair>> adj, int src, int V) {
        // first get topo sort in stack
        boolean[] vis = new boolean[V];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < V; i++)
            if (vis[i] == false)
                getTOPO(adj, stk, i, vis);
        // now make distance array, initialize all with infinity
        int[] dis = new int[V];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;
        while (!stk.isEmpty()) {
            int node = stk.pop();
            if (dis[node] != Integer.MAX_VALUE) {
                for (Pair it : adj.get(node)) {
                    if (dis[node] + it.weight < dis[it.dest])
                        dis[it.dest] = dis[node] + it.weight;
                }
            }
        }
        return dis;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int V = 6;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V + 1; i++)
            adj.add(new ArrayList<Pair>());
        for (int i = 0; i < V + 1; i++) {
            int u = scn.nextInt();
            int v = scn.nextInt();
            int w = scn.nextInt();
            addEdgeWeight(adj, u, v, w);
        }

        int[] shortestPaths = getShortestPaths(adj, 0, V);
        for (int x : shortestPaths)
            System.out.print(x + " ");
    }
}
