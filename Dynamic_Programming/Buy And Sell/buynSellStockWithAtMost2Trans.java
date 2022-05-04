import java.util.*;
import java.io.*;

public class buynSellStockWithAtMost2Trans {

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
        return memoization(0, 1, prices, 2, qb);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++)
            prices[i] = scn.nextInt();
        int maxProfit = findProfit(prices);
        System.out.println(maxProfit);
    }
}
