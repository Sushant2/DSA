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


    public static int maxGold(int[][] arr) {
        int maxGold = Integer.MIN_VALUE;
        int[][] qb = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++)
                qb[i][j] = -1;
        for (int i = 0; i < arr.length; i++) {
            // int tempMax = recursive(arr, i, 0);
            int tempMax = memoized(arr, i, 0, qb);
            maxGold = Math.max(tempMax, maxGold);
        }
        return maxGold;
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
