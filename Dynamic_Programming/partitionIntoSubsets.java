import java.io.*;
import java.util.*;

//DEDUCED RECURRENCE RELATION : fn(n,k) = fn(n-1,k-1) + fn(n-1,k)*k;

public class partitionIntoSubsets {

    // time comp :O(2^n),
    public static int recursive(int n, int k) {
        // base case
        if (n == 0 || k == 0 || n < k)
            return 0;
        if (n == k || k == 1)
            return 1;

        return recursive(n - 1, k - 1) + recursive(n - 1, k) * k;
    }

    // time compl : O(n*k) , space comp :O(n*k)
    public static int memoized(int n, int k, int[][] qb) {
        // base case
        if (n == 0 || k == 0 || n < k)
            return 0;
        if (n == k || k == 1)
            return 1;
        if (qb[n][k] != -1)
            return qb[n][k];

        return qb[n][k] = memoized(n - 1, k - 1, qb) + memoized(n - 1, k, qb) * k;
    }

    public static int tabulation(int n, int k) {
        // step1: create storage & assign meaning to cells
        long[][] dp = new long[n + 1][k + 1];
        // dp[n][k] means n bando ko , k teams m kitne tareeke se baant skte h
        // step2: direction of problem
        // d[1][1] is small for 1 player one team, dp[n][k] is bigger for n players k
        // team
        // step3: travel & solve
        for (int p = 1; p < dp.length; p++) {
            for (int t = 1; t < dp[0].length; t++) {
                if (p < t)
                    dp[p][t] = 0;
                else if (p == t || t == 1)
                    dp[p][t] = 1;
                else {
                    dp[p][t] = dp[p - 1][t - 1] + dp[p - 1][t] * t;
                }
            }
        }
        return (int) dp[n][k];
    }

    public static int findWays(int n, int k) {
        // return recursive(n, k);
        int[][] qb = new int[n + 1][k + 1];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        for (int i = 0; i < qb.length; i++)
            qb[i][0] = 0;
        for (int j = 0; j < qb[0].length; j++)
            qb[0][j] = 0;
        // return memoized(n, k, qb);
        return tabulation(n, k);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(); // no. of people
        int k = scn.nextInt(); // no. of teams
        int totalWays = findWays(n, k);
        System.out.println(totalWays);

    }
}
