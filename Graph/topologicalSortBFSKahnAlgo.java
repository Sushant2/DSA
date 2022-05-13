import java.io.*;
import java.util.*;

//!KAHN's ALGO
// take an array to store indegree of nodes - intially all indegree's mark as 0
// take a queue data structure, it'll store the nodes which having indegree '0'
// simply implicate the BFS algo
// when you take out the node from queue

public class topologicalSortBFSKahnAlgo {

    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    public static int[] getTopoSort(ArrayList<ArrayList<Integer>> adj, int V) {
        int[] topo = new int[V]; // to store final topological ordering
        // go through every adjacent nodes, for an adj node there will be an incoming
        // edge, so inc the indegree for those adj nodes
        int[] indegree = new int[V + 1];
        for (int i = 0; i < V; i++) {
            for (Integer x : adj.get(i))
                indegree[x]++;
        }
        Queue<Integer> q = new LinkedList<>();
        // initally fill queue with the nodes having indegree 0
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
        int ind = 0;
        while (!q.isEmpty()) {
            Integer node = q.poll();
            topo[ind++] = node;
            for (Integer it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0)
                    q.add(it);
            }
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
