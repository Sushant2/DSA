import java.util.*;
import java.io.*;

public class buynSellStocksInfiniteTrans {

    public static int findProfit(int[] prices) {
        int bd = 0; // buying date
        int sd = 0; // selling date
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                sd++;
            else {
                profit += prices[sd] - prices[bd];
                bd = sd = i;
            }
        }
        // at last we don't have a dip, so find it's profit of upstrokes
        profit += prices[sd] - prices[bd];
        return profit;
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
