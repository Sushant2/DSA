import java.io.*;
import java.util.*;

public class minCostPath {

    // public static int min = Integer.MIN_VALUE;

    public static int recursive(int sr, int sc, int dr, int dc, int[][] arr) {
        // -ve base case
        if (sr > dr || sc > dc)
            return Integer.MAX_VALUE;
        // +ve case
        if (sr == dr && sc == dc)
            return arr[sr][sc];

        int leftMin = recursive(sr + 1, sc, dr, dc, arr);
        int rightMin = recursive(sr, sc + 1, dr, dc, arr);
        int min = Math.min(leftMin, rightMin);

        return arr[sr][sc] + min;
    }

    public static int minCostInMaze(int[][] arr) {
        return recursive(0, 0, arr.length - 1, arr[0].length - 1, arr);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = scn.nextInt();
        int cost = minCostInMaze(arr);
        System.out.println(cost);
    }
}