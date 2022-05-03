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

    public static int findProfit(int[] prices) {
        // return recursive(0, 1, prices);
        int[][] qb = new int[2][prices.length + 1];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        return memoized(0, 1, prices, qb);
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