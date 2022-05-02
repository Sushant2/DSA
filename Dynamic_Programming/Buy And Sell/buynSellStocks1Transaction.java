import java.io.*;
import java.rmi.dgc.Lease;
import java.util.*;

//! NOT A DP Question
public class buynSellStocks1Transaction {

    public static int buyAndSell(int[] prices) {
        int lsf = Integer.MAX_VALUE; // least so far
        int maxProfit = 0;
        int pist = 0; // profit if sold today
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < lsf)
                lsf = prices[i];
            // calculating today's profit if sold
            pist = prices[i] - lsf;
            if (pist > maxProfit)
                maxProfit = pist;
        }
        return maxProfit;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++)
            prices[i] = scn.nextInt();
        int ans = buyAndSell(prices);
        System.out.println(ans);
    }
}
