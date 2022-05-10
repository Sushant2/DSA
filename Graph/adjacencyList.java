import java.util.*;
import java.io.*;

public class adjacencyList {
    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void printGraph(ArrayList<ArrayList<Integer>> adj) {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\nVertex " + i + ":");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> " + adj.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<Integer>());
        for (int i = 0; i < m; i++) {
            int u = scn.nextInt();
            int v = scn.nextInt();
            addEdge(adj, u, v);
        }
        printGraph(adj);
        scn.close();
    }
}

// 5 6
// 1 2 3
// 1 4 12
// 1 5 4
// 2 3 5
// 3 5 2
// 5 4 7