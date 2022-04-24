import java.util.*;
import java.io.*;

public class unboundedKnapSack {

    public static int recursive(int[] wt, int[] val, int W, int n) {
        // base case
        if (n == 0 || W == 0)
            return 0;
        if (wt[n - 1] <= W)
            return Math.max(val[n - 1] + recursive(wt, val, W - wt[n - 1], n), recursive(wt, val, W, n - 1));
        else
            return recursive(wt, val, W, n - 1);
    }

    public static int memoized(int[] wt, int[] val, int W, int n, int[][] qb) {
        if (n == 0 || W == 0)
            return qb[n][W] = 0;
        if (qb[n][W] != -1)
            return qb[n][W];

        if (wt[n - 1] <= W) {
            return qb[n][W] = Math.max(val[n - 1] + memoized(wt, val, W - wt[n - 1], n, qb),
                    memoized(wt, val, W, n - 1, qb));
        } else {
            return qb[n][W] = memoized(wt, val, W, n - 1, qb);
        }
    }

    public static int unboundedKnap(int[] wt, int[] val, int W, int n) {
        // return recursive(wt, val, W, n);
        int[][] qb = new int[n + 1][W + 1];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        return memoized(wt, val, W, n, qb);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] wt = new int[n];
        for (int i = 0; i < n; i++)
            wt[i] = scn.nextInt();
        int[] val = new int[n];
        for (int i = 0; i < n; i++)
            val[i] = scn.nextInt();
        int W = scn.nextInt();
        int maxProfit = unboundedKnap(wt, val, W, n);
        System.out.println(maxProfit);
    }
}
