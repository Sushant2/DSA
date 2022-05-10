import java.io.*;
import java.util.*;

public class adjacencyMatrix {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] adjMat = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int u = scn.nextInt();
            int v = scn.nextInt();
            adjMat[u][v] = 1;
            adjMat[v][u] = 1;
        }
        for (int i = 0; i < adjMat.length; i++) {
            for (int j = 0; j < adjMat[0].length; j++)
                System.out.print(adjMat[i][j] + " ");
            System.out.println();
        }
    }
}
