import java.io.*;
import java.util.*;

public class adjacencyMatrix {

    public static class Pair {
        int val, weight;

        Pair(int val, int weight) {
            this.val = val;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        Pair[][] adjMat = new Pair[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int u = scn.nextInt();
            int v = scn.nextInt();
            int weight = scn.nextInt();
            adjMat[u][v] = new Pair(1, weight);
            adjMat[v][u] = new Pair(1, weight);
        }
        for (int i = 0; i < adjMat.length; i++) {
            for (int j = 0; j < adjMat[0].length; j++)
                if (adjMat[i][j] != null)
                    System.out.print(adjMat[i][j].weight + " ");
                else
                    System.out.print("0 ");
            System.out.println();
        }
    }
}
