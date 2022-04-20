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

    public static int tabulation(int[][] arr) {
        // step 1. create storage & assign meaning to cell
        int[][] dp = new int[arr.length][arr[0].length];
        // meaning of cell - kia cell will store the min cost of path from that cell to
        // destination cell
        // step 2. direction of problem
        // choti problem - destination cell se destination cell pr jana, badi problem -
        // (0,0) se destination cell par jana
        // step 3. travel & solve

        // ! we've divided the dp 2d array into 4 parts -
        // 1. last cell ko alag treat - direct value daldenge from arr
        // 2. last row ko alag treat - vertical nhi jaa skte
        // 3. last column ko alag treat - horizontal nhi jaa skte
        // 4. rest of it ko alag treat

        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                // last cell
                if (i == dp.length - 1 && j == dp[0].length - 1)
                    dp[i][j] = arr[i][j];
                // last row
                else if (i == dp.length - 1)
                    dp[i][j] = dp[i][j + 1] + arr[i][j];
                // last column
                else if (j == dp[0].length - 1)
                    dp[i][j] = dp[i + 1][j] + arr[i][j];
                // rest of the 2d dp table
                else
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + arr[i][j];
            }
        }
        return dp[0][0];
    }

    public static int minCostInMaze(int[][] arr) {
        // return recursive(0, 0, arr.length - 1, arr[0].length - 1, arr);
        int[][] qb = new int[arr.length + 1][arr[0].length + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            for (int j = 0; j < arr[0].length + 1; j++) {
                qb[i][j] = -1;
            }
        }
        // return memoized(0, 0, arr.length - 1, arr[0].length - 1, arr, qb);
        return tabulation(arr);
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