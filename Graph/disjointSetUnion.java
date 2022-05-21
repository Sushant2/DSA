import java.util.*;
import java.io.*;

//! DISJOINT SET USING PATH COMPRESSION & UNION BY RANKING
//? time compl : O(4alpha) ~ O(4) or constant to perform union operation
public class disjointSetUnion {

    public static int[] parent;
    public static int[] rank;
    public static int n;

    public static void makeSet(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parent[i] = i;
    }

    public static int findPar(int node) {
        if (node == parent[node])
            return node;
        // return findPar(parent[node]);
        // second return will compress the path
        return parent[node] = findPar(parent[node]);
    }

    public static void union(int u, int v) {
        u = findPar(u);
        v = findPar(v);
        if (rank[u] < rank[v])
            // attach u to v i.e, parent of u now becomes v
            parent[u] = v;
        else if (rank[u] > rank[v])
            // attach v to u i.e, parent of v now becomes u
            parent[v] = u;
        else {
            // attack any node to any
            // inc rank by 1 of guy to whom you are attaching
            parent[v] = u;
            rank[u]++;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = 9;
        makeSet(n);
        union(1, 2);
        union(2, 3);
        union(4, 5);
        union(6, 7);
        union(5, 6);
        union(3, 7);
        union(8, 9);
        // check if node u & v are a part of same component
        int u = scn.nextInt();
        int v = scn.nextInt();
        if (findPar(u) == findPar(v))
            System.out.println("YES");
        else
            System.out.println("NO");

        scn.close();
    }
}