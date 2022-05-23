import java.util.*;
import java.io.*;

public class bellmanFordAlgo {

    public static class Node {

        int u, v, w;

        Node(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdgeWeight(ArrayList<Node> edges, int u, int v, int w) {
        edges.add(new Node(u, v, w));
    }

    public static void bellmanFord(ArrayList<Node> edges, int V, int src) {
        int[] dis = new int[V];
        Arrays.fill(dis, 1000000);
        dis[src] = 0;
        // do V-1 relaxations for all edges
        for (int i = 1; i <= V - 1; i++) {
            for (Node node : edges) {
                if (dis[node.u] + node.w < dis[node.v])
                    dis[node.v] = dis[node.u] + node.w;
            }
        }
        // check if there is a -ve cycle or not
        int flag = 0;
        for (Node node : edges) {
            if (dis[node.u] + node.w < dis[node.v]) {
                flag = 1;
                System.out.println("Nagative Cycle");
                break;
            }
        }

        // if there is no negative cycle -
        if (flag == 0) {
            for (int i = 0; i < V; i++) {
                System.out.println(i + " " + dis[i]);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        int V = 6;

        // as we need only edges with weights, so not creating adjacency list
        ArrayList<Node> edges = new ArrayList<>();
        addEdgeWeight(edges, 3, 2, 6);
        addEdgeWeight(edges, 5, 3, 1);
        addEdgeWeight(edges, 0, 1, 5);
        addEdgeWeight(edges, 1, 5, -3);
        addEdgeWeight(edges, 1, 2, -2);
        addEdgeWeight(edges, 3, 4, -2);
        addEdgeWeight(edges, 2, 4, 3);

        bellmanFord(edges, V, 0);
    }
}
