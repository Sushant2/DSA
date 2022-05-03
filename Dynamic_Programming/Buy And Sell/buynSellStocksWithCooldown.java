import java.util.*;
import java.io.*;

public class buynSellStocksWithCooldown {
    // buy = 1, means true we can buy
    // buy = 0, means false we can't buy, so we sell
    public static int recursive(int idx, int buy, int[] prices) {
        // base case
        if (idx >= prices.length)
            return 0;
        if (buy == 1)
            return Math.max(-prices[idx] + recursive(idx + 1, 0, prices), 0 + recursive(idx + 1, 1, prices));
        else
            return Math.max(prices[idx] + recursive(idx + 2, 1, prices), 0 + recursive(idx + 1, 0, prices));
    }

    public static int findProfit(int[] prices) {
        return recursive(0, 1, prices);
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