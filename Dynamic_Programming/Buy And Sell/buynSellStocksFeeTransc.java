import java.util.*;
import java.io.*;

public class buynSellStocksFeeTransc {

    public static int findProfit(int[] arr, int fee) {
        // on first day
        int obsp = -arr[0]; // old bought state profit
        int ossp = 0; // old sold state profit
        for (int i = 1; i < arr.length; i++) {
            int nbsp = 0; // new bought state profit
            int nssp = 0; // new sold state profit
            // to make new bought profit
            if (ossp - arr[i] > obsp)
                nbsp = ossp - arr[i];
            else
                nbsp = obsp;
            // to make new sold profit
            if (arr[i] - fee + obsp > ossp)
                nssp = arr[i] - fee + obsp;
            else
                nssp = ossp;

            obsp = nbsp;
            ossp = nssp;
        }
        return ossp;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int fees = scn.nextInt();
        int profit = findProfit(arr, fees);
        System.out.println(profit);
    }
}
