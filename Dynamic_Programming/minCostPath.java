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

    public static int memoized(int sr, int sc, int dr, int dc, int[][] arr, int[][] qb) {
        // -ve base case
        if (sr > dr || sc > dc)
            return Integer.MAX_VALUE;
        // +ve base case
        if (sr == dr && sc == dc)
            return arr[sr][sc];
        // if answer to that subproblem already exists in qb
        if (qb[sr][sc] != -1)
            return qb[sr][sc];

        int leftMin = memoized(sr + 1, sc, dr, dc, arr, qb);
        int rightMin = memoized(sr, sc + 1, dr, dc, arr, qb);
        int min = Math.min(leftMin, rightMin);
        qb[sr][sc] = min + arr[sr][sc];

        return arr[sr][sc] + min;
    }

    public static int minCostInMaze(int[][] arr) {
        // return recursive(0, 0, arr.length - 1, arr[0].length - 1, arr);
        int[][] qb = new int[arr.length + 1][arr[0].length + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            for (int j = 0; j < arr[0].length + 1; j++) {
                qb[i][j] = -1;
            }
        }
        return memoized(0, 0, arr.length - 1, arr[0].length - 1, arr, qb);
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