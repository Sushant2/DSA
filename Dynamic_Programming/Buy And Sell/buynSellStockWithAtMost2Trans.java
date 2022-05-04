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

    public static int findProfit(int[] prices) {
        // idx, buytrue, prices, cap
        return recursive(0, 1, prices, 2);
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
