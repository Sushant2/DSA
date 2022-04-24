import java.io.*;
import java.util.*;

import javax.sound.sampled.ReverbType;

public class zeroOneKnapSack {

    public static int recursive(int[] wt, int[] val, int W, int n) {
        // base case
        if (n == 0 || W == 0)
            return 0;

        if (wt[n - 1] <= W)
            return Math.max(val[n - 1] + recursive(wt, val, W - wt[n - 1], n - 1), recursive(wt, val, W, n - 1));
        else
            return recursive(wt, val, W, n - 1);
    }

    public static int memoized(int[] wt, int[] val, int W, int n, int[][] qb) {
        // base condition
        if (W == 0 || n == 0)
            return 0;
        if (qb[n][W] != 0)
            return qb[n][W];

        if (wt[n - 1] <= W) {
            qb[n][W] = Math.max(val[n - 1] + memoized(wt, val, W - wt[n - 1], n - 1, qb),
                    memoized(wt, val, W, n - 1, qb));
            return qb[n][W];
        } else {
            return qb[n][W] = memoized(wt, val, W, n - 1, qb);
        }
    }

    public static int tabulation(int[] wt, int[] val, int W, int n) {
        // step 1: create storage & assign meaning
        int[][] dp = new int[n + 1][W + 1];
        // meaning - dp[i][j] - j balls ke lie i tak ki team kitne runs score kr skti h
        // step 2: direction of problem
        // choti - dp[0][0] & badi problem - dp[n][W]
        // step 3: travel & solve
        // 0th row & 0th row m already 0 pada hai, tho loop i =1, j=1 se chalenge
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // if balls are sufficient -
                // wt[i-1] isiliye kyoki dp array ka i is corresponds to items arrays of i-1
                if (j >= wt[i - 1]) {
                    // then we'll do batting
                    int remW = j - wt[i - 1]; // remaining balls
                    // wehn i bat & it's score is greater than if he does't bat
                    if (dp[i - 1][remW] + val[i - 1] > dp[i - 1][j])
                        dp[i][j] = dp[i - 1][remW] + val[i - 1];
                    else
                        // wehn i doesn't bat
                        dp[i][j] = dp[i - 1][j];
                } else
                    // when balls are not suffcient, he can't do batting
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][W];
    }

    public static int _01knapSack(int[] wt, int[] val, int W, int n) {
        // return recursive(wt, val, W, n);
        int[][] qb = new int[n + 1][W + 1];
        // return memoized(wt, val, W, n, qb);
        return tabulation(wt, val, W, n);
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
        int maxProfit = _01knapSack(wt, val, W, n);
        System.out.println(maxProfit);
    }
}
