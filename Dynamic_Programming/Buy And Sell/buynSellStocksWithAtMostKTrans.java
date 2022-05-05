import java.io.*;
import java.util.*;

public class buynSellStocksWithAtMostKTrans {
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

    public static int findProfit(int[] prices, int k) {
        return recursive(0, 1, k, prices);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int k = scn.nextInt();
        int maxProfit = findProfit(arr, k);
        System.out.println(maxProfit);
    }
}