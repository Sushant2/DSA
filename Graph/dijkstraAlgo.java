import java.io.*;
import java.util.*;

public class dijkstraAlgo {
    public static class Pair {
        int dest;
        int weight;

        Pair(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static class PairCompareWeight implements Comparator<Pair> {
        public int compare(Pair s1, Pair s2) {
            return s1.weight - s2.weight;
        }
    }

    public static void addEdgeWeight(ArrayList<ArrayList<Pair>> adj, int u, int v, int w) {
        adj.get(u).add(new Pair(v, w));
        adj.get(v).add(new Pair(u, w));
    }

    public static int[] getShortPath(ArrayList<ArrayList<Pair>> adj, int V, int src) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // PriorityQueue<Pair> pq = new PriorityQueue<Pair>(V, new Pair());

        PriorityQueue<Pair> pq = new PriorityQueue<>(new PairCompareWeight());
        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair p = pq.remove();
            for (Pair it : adj.get(p.dest)) {
                if (dist[p.dest] + it.weight < dist[it.dest]) {
                    dist[it.dest] = dist[p.dest] + it.weight;
                    pq.add(new Pair(it.dest, dist[it.dest]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        // it's same as shortest path in undirected graph, but there we have unit
        // weights & here we've different weights!
        Scanner scn = new Scanner(System.in);
        int V = 5;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<ArrayList<Pair>>();
        for (int i = 0; i < V + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < V + 1; i++) {
            int u = scn.nextInt();
            int v = scn.nextInt();
            int w = scn.nextInt();
            addEdgeWeight(adj, u, v, w);
        }

        int[] dist = getShortPath(adj, V, 1);
        for (int i = 1; i < dist.length; i++)
            System.out.print(dist[i]+ " ");
    }
}
