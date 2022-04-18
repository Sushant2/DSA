import java.util.*;
import java.io.*;

public class climbStairsWithMinMoves {

    public static int recursive(int i, int n, int[] arr) {
        if (i > n)
            return Integer.MAX_VALUE;
        if (i == n)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= arr[i]; j++) {
            int ans = recursive(i + j, n, arr);
            // jab koi na koi move aa rha ho tab hi min check krenge
            if (ans != Integer.MAX_VALUE)
                min = Math.min(min, ans + 1);
        }
        return min;
    }

    public static int memoized(int i, int n, int[] arr, int[] qb) {
        if (i > n)
            return Integer.MAX_VALUE;
        if (i == n)
            return 0;

        if (qb[i] != -1)
            return qb[i];
        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= arr[i]; j++) {
            int ans = recursive(i + j, n, arr);
            // jab koi na koi move aa rha ho tab hi min check krenge
            if (ans != Integer.MAX_VALUE)
                min = Math.min(min, ans + 1);
        }
        qb[i] = min;
        return min;
    }

    public static int tabulation(int n, int[] arr) {
        // step 1. create storage & assign meaning to cells
        // making table of INTEGER type to store null as well
        Integer[] dp = new Integer[n + 1];
        // meaning of cell - dp[i] ye bataega ki, i to n kint minimum moves m jaa skte h
        // base condition of memoised is initalised in tabulation
        dp[n] = 0; // n->n, is zero moves
        for (int i = n - 1; i >= 0; i--) {
            // we'll only work when arr[i]!=0, if arr[i] = 0, means no moves/no jump &
            // arr[i] = 0, mein null ayega isiiye use don;t touch, null pade rhnedo
            if (arr[i] > 0) {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= arr[i] && i + j < dp.length; j++) {
                    if (dp[i + j] != null)
                        min = Math.min(min, dp[i + j]);
                }
                // edge case - ki hum jaha bhi gye, waha bhi null pada hai, to hum bhi null
                // hojayenge, otherwise moves mein 1 add krlenge
                if (min != Integer.MAX_VALUE)
                    dp[i] = min + 1;
            }
        }
        return dp[0];
    }

    public static int climbStairs(int n, int[] arr) {
        // return recursive(0, n, arr);
        int[] qb = new int[n + 1];
        Arrays.fill(qb, -1);
        // return memoized(0, n, arr, qb);
        return tabulation(n, arr);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int minMoves = climbStairs(n, arr);
        System.out.println(minMoves);
    }
}
