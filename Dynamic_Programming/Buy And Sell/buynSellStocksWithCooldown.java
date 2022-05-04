import java.util.*;
import java.io.*;

public class buynSellStocksWithCooldown {
    // buy = 1, means true we can buy
    // buy = 0, means false we can't buy, so we sell
    // ! time compl : O(2^n), space compl : O(n)
    public static int recursive(int idx, int buy, int[] prices) {
        // base case
        if (idx >= prices.length)
            return 0;
        if (buy == 1)
            return Math.max(-prices[idx] + recursive(idx + 1, 0, prices), 0 + recursive(idx + 1, 1, prices));
        else
            return Math.max(prices[idx] + recursive(idx + 2, 1, prices), 0 + recursive(idx + 1, 0, prices));
    }

    // ! time comp: O(n), space comp: O(n)
    public static int memoized(int idx, int buy, int[] prices, int[][] qb) {
        // base case
        if (idx >= prices.length)
            return 0;
        if (qb[buy][idx] != -1)
            return qb[buy][idx];
        if (buy == 1)
            return qb[buy][idx] = Math.max(-prices[idx] + memoized(idx + 1, 0, prices, qb),
                    0 + memoized(idx + 1, 1, prices, qb));
        else
            return qb[buy][idx] = Math.max(prices[idx] + memoized(idx + 2, 1, prices, qb),
                    0 + memoized(idx + 1, 0, prices, qb));
    }

    // time comp : O(n), space comp : O(n)
    public static int tabulation(int[] prices) {
        // step 1: create storage & assign meaning to cells
        int[][] dp = new int[2][prices.length + 2];
        // dp[1][idx] means for day idx, what will be the max profit if we're buying the
        // stocks
        // step 2: direction of problem
        // base case if small problem , so we'll iterate reversely
        // step 3: travel & solve
        for (int idx = prices.length - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1)
                    dp[buy][idx] = Math.max(-prices[idx] + dp[0][idx + 1], 0 + dp[1][idx + 1]);
                else
                    dp[buy][idx] = Math.max(prices[idx] + dp[1][idx + 2], 0 + dp[0][idx + 1]);
            }
        }
        return dp[1][0];
    }

    // ! PEPCODING's SOLUTION
    // ! time comp :O(n), space comp : O(1)
    public static int mostOptimized(int[] prices) {
        // on the first day
        int obsp = -prices[0]; // old bought state profit
        int ossp = 0; // old sold state profit
        int ocsp = 0;// old cooldown state profit
        for (int i = 1; i < prices.length; i++) {
            // new bought, sold, cooldown state profit
            int nbsp = 0;
            int nssp = 0;
            int ncsp = 0;
            // new bought state profit
            if (ocsp - prices[i] > obsp)
                nbsp = ocsp - prices[i];
            else
                nbsp = obsp;
            // new sold state profit
            if (obsp + prices[i] > ossp)
                nssp = obsp + prices[i];
            else
                nssp = ossp;
            // new cooldown state profit
            if (ossp > ocsp)
                ncsp = ossp;
            else
                ncsp = ocsp;

            obsp = nbsp;
            ossp = nssp;
            ocsp = ncsp;
        }

        return ossp;
    }

    public static int findProfit(int[] prices) {
        // return recursive(0, 1, prices);
        int[][] qb = new int[2][prices.length + 1];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        // return memoized(0, 1, prices, qb);
        // return tabulation(prices);
        return mostOptimized(prices);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scn.nextInt();
        }

        int maxProfit = findProfit(prices);
        System.out.println(maxProfit);
    }
}