import java.io.*;
import java.util.*;

public class buynSellStocksWithAtMostKTrans {
    // time comp : O(2^n), space compl : O(n)
    public static int recursive(int idx, int buy, int k, int[] prices) {
        // base case
        if (idx == prices.length || k == 0)
            return 0;
        if (buy == 1) {
            return Math.max(-prices[idx] + recursive(idx + 1, 0, k, prices), 0 + recursive(idx + 1, 1, k, prices));
        } else {
            return Math.max(prices[idx] + recursive(idx + 1, 1, k - 1, prices), 0 + recursive(idx + 1, 0, k, prices));
        }
    }

    // time comp: O(n*2*K), space comp: O(n*2*k)
    public static int memoized(int idx, int buy, int k, int[] prices, int[][][] qb) {
        // base case
        if (idx == prices.length || k == 0)
            return 0;
        if (qb[idx][buy][k] != -1)
            return qb[idx][buy][k];
        if (buy == 1) {
            return qb[idx][buy][k] = Math.max(-prices[idx] + memoized(idx + 1, 0, k, prices, qb),
                    0 + memoized(idx + 1, 1, k, prices, qb));
        } else {
            return qb[idx][buy][k] = Math.max(prices[idx] + memoized(idx + 1, 1, k - 1, prices, qb),
                    0 + memoized(idx + 1, 0, k, prices, qb));
        }
    }

    // time comp: O(n*2*K), space comp: O(n*2*k)
    public static int tabulation(int k, int[] prices) {
        int[][][] dp = new int[prices.length + 1][2][k + 1];
        for (int idx = prices.length - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (buy == 1) {
                        dp[idx][buy][cap] = Math.max(-prices[idx] + dp[idx + 1][0][cap], 0 + dp[idx + 1][1][cap]);
                    } else {
                        dp[idx][buy][cap] = Math.max(prices[idx] + dp[idx + 1][1][cap - 1], 0 + dp[idx + 1][0][cap]);
                    }
                }
            }
        }
        return dp[0][1][k];
    }

    public static int findProfit(int[] prices, int k) {
        // return recursive(0, 1, k, prices);
        int[][][] qb = new int[prices.length][2][k + 1];
        for (int[][] x : qb) {
            for (int[] y : x) {
                Arrays.fill(y, -1);
            }
        }
        // return memoized(0, 1, k, prices, qb);
        return tabulation(k, prices);
    }

    // ! we can also solve this using 2D dp array
    // parameters as idx, & transaction number : 2*k
    public static int recursive2(int idx, int transNo, int k, int[] prices) {
        if (idx == prices.length || transNo == k)
            return 0;
        if (transNo % 2 == 0)
            return Math.max(-prices[idx] + recursive(idx + 1, transNo + 1, k, prices),
                    0 + recursive(idx + 1, transNo, k, prices));
        else
            return Math.max(prices[idx] + recursive(idx + 1, transNo + 1, k, prices),
                    0 + recursive(idx + 1, transNo, k, prices));
    }

    public static int memoized2(int idx, int transNo, int k, int[] prices, int[][] qb) {
        if (idx == prices.length || transNo == k)
            return 0;
        if (qb[idx][transNo] != -1)
            return qb[idx][transNo];
        if (transNo % 2 == 0)
            return qb[idx][transNo] = Math.max(-prices[idx] + memoized2(idx + 1, transNo + 1, k, prices, qb),
                    0 + memoized2(idx + 1, transNo, k, prices, qb));
        else
            return qb[idx][transNo] = Math.max(prices[idx] + memoized2(idx + 1, transNo + 1, k, prices, qb),
                    0 + memoized2(idx + 1, transNo, k, prices, qb));
    }

    public static int tabulation2(int k, int[] prices) {
        int[][] dp = new int[prices.length + 1][2 * k + 1];
        for (int idx = prices.length - 1; idx >= 0; idx--) {
            for (int transNo = 2 * k - 1; transNo >= 0; transNo--) {
                if (transNo % 2 == 0)
                    dp[idx][transNo] = Math.max(-prices[idx] + dp[idx + 1][transNo + 1], 0 + dp[idx + 1][transNo]);
                else
                    dp[idx][transNo] = Math.max(prices[idx] + dp[idx + 1][transNo + 1], 0 + dp[idx + 1][transNo]);
            }
        }
        return dp[0][0];
    }

    public static int findProfit1(int[] prices, int k) {
        // return recursive2(0, 0, 2 * k, prices);
        int[][] qb = new int[prices.length + 1][2 * k + 1];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        // return memoized2(0, 0, 2 * k, prices, qb);
        return tabulation2(k, prices);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int k = scn.nextInt();
        // int maxProfit = findProfit(arr, k);
        int maxProfit = findProfit1(arr, k);
        System.out.println(maxProfit);
        scn.close();
    }
}