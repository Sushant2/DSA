import java.util.*;
import java.io.*;

public class buynSellStockWithAtMost2Trans {

    // time compl :O(2^n), space comp : O(n)
    public static int recursive(int idx, int buy, int[] prices, int cap) {
        // base case
        if (idx == prices.length || cap == 0)
            return 0;

        // if buy = true
        if (buy == 1)
            return Math.max(-prices[idx] + recursive(idx + 1, 0, prices, cap), 0 + recursive(idx + 1, 1, prices, cap));
        else
            return Math.max(prices[idx] + recursive(idx + 1, 1, prices, cap - 1),
                    0 + recursive(idx + 1, 0, prices, cap));
    }

    // time comp : O(n*2*3), space comp : O(n)
    public static int memoization(int idx, int buy, int[] prices, int cap, int[][][] qb) {
        // base case
        if (idx == prices.length || cap == 0)
            return 0;

        if (qb[idx][buy][cap] != -1)
            return qb[idx][buy][cap];

        // if buy = true
        if (buy == 1)
            return qb[idx][buy][cap] = Math.max(-prices[idx] + memoization(idx + 1, 0, prices, cap, qb),
                    0 + memoization(idx + 1, 1, prices, cap, qb));
        else
            return qb[idx][buy][cap] = Math.max(prices[idx] + memoization(idx + 1, 1, prices, cap - 1, qb),
                    0 + memoization(idx + 1, 0, prices, cap, qb));
    }

    // time comp : O(n*2*3), space comp : O(n)
    public static int tabulation(int[] prices) {
        // step1: create storage & assign meaning to cells
        int[][][] dp = new int[prices.length + 1][2][3];
        // dp[idx][1][2] means what is the profit at day "idx" when we buying & our
        // transaction is yet to complete
        // initialization is base case of memoization, but no need here, by default
        // value is 0

        // direction of problem : choti problem base case m
        // we'll iterate buy & cap in reverse of memoization
        // step 3 : travel & solve
        for (int idx = prices.length - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 1)
                        dp[idx][buy][cap] = Math.max(-prices[idx] + dp[idx + 1][0][cap], 0 + dp[idx + 1][1][cap]);
                    else
                        dp[idx][buy][cap] = Math.max(prices[idx] + dp[idx + 1][1][cap - 1], 0 + dp[idx + 1][0][cap]);
                }
            }
        }
        return dp[0][1][2];
    }

    public static int findProfit(int[] prices) {
        // idx, buytrue, prices, cap
        // return recursive(0, 1, prices, 2);
        // we can see 3 parameters are changing-idx,buy,cap
        // so creating 3d dp array
        // 2 states for buy, & 3 states of cap, 2,1, 0(means no transaction)
        int[][][] qb = new int[prices.length][2][3];
        for (int[][] x : qb) {
            for (int[] y : x)
                Arrays.fill(y, -1);
        }
        // return memoization(0, 1, prices, 2, qb);
        return tabulation(prices);
    }

    // ! USING 2D DP ARRAY - recursion, memoization, tabulation, space optimization

    // time comp : O(2^n), space comp : O(n)
    public static int recursive2(int idx, int tn, int[] prices) {
        // base case
        if (idx == prices.length || tn == 4)
            return 0;

        // if transaction number is even then buy
        if (tn % 2 == 0)
            return Math.max(-prices[idx] + recursive2(idx + 1, tn + 1, prices), 0 + recursive2(idx + 1, tn, prices));
        // if transaction number is odd then buy
        else
            return Math.max(prices[idx] + recursive2(idx + 1, tn + 1, prices), 0 + recursive2(idx + 1, tn, prices));
    }

    // time comp : O(n*4), space comp : O(n*4)
    public static int memoization2(int idx, int tn, int[] prices, int[][] qb) {
        // base case
        if (idx == prices.length || tn == 4)
            return 0;

        if (qb[idx][tn] != -1)
            return qb[idx][tn];

        // if transaction number is even then buy
        if (tn % 2 == 0)
            return qb[idx][tn] = Math.max(-prices[idx] + memoization2(idx + 1, tn + 1, prices, qb),
                    0 + memoization2(idx + 1, tn, prices, qb));
        // if transaction number is odd then buy
        else
            return qb[idx][tn] = Math.max(prices[idx] + memoization2(idx + 1, tn + 1, prices, qb),
                    0 + memoization2(idx + 1, tn, prices, qb));
    }

    // time comp : O(n*4), space comp : O(n*4)
    public static int tabulation2(int[] prices) {
        int[][] dp = new int[prices.length + 1][5];
        for (int idx = prices.length - 1; idx >= 0; idx--) {
            for (int tn = 3; tn >= 0; tn--) {
                if (tn % 2 == 0) {
                    dp[idx][tn] = Math.max(-prices[idx] + dp[idx + 1][tn + 1], 0 + dp[idx + 1][tn]);
                } else {
                    dp[idx][tn] = Math.max(prices[idx] + dp[idx + 1][tn + 1], 0 + dp[idx + 1][tn]);
                }
            }
        }
        return dp[0][0];
    }

    public static int mostOptimized(int[] prices) {
        // approach : max sum of best transaction on the left & best transaction on the
        // right, may or maynot including that day price

        int mpist = 0; // max profit if sold today
        int leastsf = prices[0]; // least so far
        int[] dpl = new int[prices.length]; // dp on the left -> max profit if sold uptotoday
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < leastsf)
                leastsf = prices[i];
            mpist = prices[i] - leastsf;
            if (mpist > dpl[i - 1])
                dpl[i] = mpist;
            else
                dpl[i] = dpl[i - 1];
        }

        int mpibt = 0;// max profit if bought today
        int maxat = prices[prices.length - 1];// max after today
        int[] dpr = new int[prices.length]; // dp on the right, max Profit if bought till today
        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] > maxat)
                maxat = prices[i];
            mpibt = maxat - prices[i];
            if (mpibt > dpr[i + 1])
                dpr[i] = mpibt;
            else
                dpr[i] = dpr[i + 1];
        }

        int op = 0; // overall profit
        for (int i = 0; i < prices.length; i++) {
            if (dpl[i] + dpr[i] > op)
                op = dpl[i] + dpr[i];
        }

        return op;
    }

    public static int findProfit2(int[] prices) {
        // idx, transaction number
        // return recursive2(0, 0, prices);
        int[][] qb = new int[prices.length][4];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        // return memoization2(0, 0, prices, qb);
        // return tabulation2(prices);
        return mostOptimized(prices);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++)
            prices[i] = scn.nextInt();
        // int maxProfit = findProfit(prices);
        int maxProfit = findProfit2(prices);
        System.out.println(maxProfit);
    }
}
