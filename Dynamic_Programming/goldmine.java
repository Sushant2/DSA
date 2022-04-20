import java.io.*;
import java.util.*;

public class goldmine {

    private static int max(int x, int y, int z) {
        if (x > y)
            return (x > z) ? x : z;
        else
            return (y > z) ? y : z;
    }

    public static int recursive(int[][] arr, int sr, int sc) {
        // -ve base case
        if (sr < 0 || sr > arr.length - 1)
            return Integer.MIN_VALUE;
        // +ve base case
        if (sc == arr[0].length - 1)
            return arr[sr][sc];

        int rup = recursive(arr, sr - 1, sc + 1);
        int right = recursive(arr, sr, sc + 1);
        int rdown = recursive(arr, sr + 1, sc + 1);

        int maxGold = max(rup, right, rdown);
        return arr[sr][sc] + maxGold;
    }

    public static int memoized(int[][] arr, int sr, int sc, int[][] qb) {
        // -ve base case
        if (sr < 0 || sr > arr.length - 1)
            return Integer.MIN_VALUE;
        if (sc == arr[0].length - 1)
            return arr[sr][sc];
        // if answer to that subproblem is already present in the qb
        if (qb[sr][sc] != -1)
            return qb[sr][sc];

        int rup = memoized(arr, sr - 1, sc + 1, qb);
        int right = memoized(arr, sr, sc + 1, qb);
        int rdown = memoized(arr, sr + 1, sc + 1, qb);

        int maxGold = max(rup, right, rdown);
        qb[sr][sc] = maxGold + arr[sr][sc];
        return qb[sr][sc];
    }

    public static int tabulation(int[][] arr) {
        // step 1. create storage & assing meaning
        int[][] dp = new int[arr.length][arr[0].length];
        // meaning to cell - dp[i][j] = means agar yaha se agar yaha se gold dig krna
        // start krte to kitna max collect krte
        // step 2. direction of problem
        // choti problem - last column, badi problem - first column, many ways to get
        // gold
        // column wise traverse from right to left
        // step 3. travel & solve
        for (int j = arr[0].length - 1; j >= 0; j--) {
            for (int i = arr.length - 1; i >= 0; i--) {
                // last column
                if (j == arr[0].length - 1)
                    dp[i][j] = arr[i][j];
                // edge case - if only one row is given - we can dig only in it's right
                else if (arr.length == 1)
                    dp[i][j] = arr[i][j] + dp[i][j + 1];
                // first row
                else if (i == 0)
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
                // last row
                else if (i == arr.length - 1)
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i - 1][j + 1]);
                // rest 2d dp arraay
                else
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], Math.max(dp[i + 1][j + 1], dp[i - 1][j + 1]));
            }
        }
        // ans is maximum from first column

        int max = dp[0][0];
        for (int i = 1; i < dp.length; i++)
            if (dp[i][0] > max)
                max = dp[i][0];
        return max;
    }

    public static int maxGold(int[][] arr) {
        // int maxGold = Integer.MIN_VALUE;
        // int[][] qb = new int[arr.length][arr[0].length];
        // for (int i = 0; i < arr.length; i++)
        // for (int j = 0; j < arr[0].length; j++)
        // qb[i][j] = -1;
        // for (int i = 0; i < arr.length; i++) {
        // int tempMax = recursive(arr, i, 0);
        // int tempMax = memoized(arr, i, 0, qb);
        // maxGold = Math.max(tempMax, maxGold);
        // }
        // return maxGold;

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
        int maxGold = maxGold(arr);
        System.out.println(maxGold);
    }
}
