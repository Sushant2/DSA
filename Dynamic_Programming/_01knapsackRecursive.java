import java.util.*;
import java.io.*;

public class _01knapsackRecursive {

    public static int knapSack(int[] wt, int[] val, int W, int n) {
        // base case
        // if items = 0 & weight of bag = 0 then maxProfit is also 0
        if (W == 0 || n == 0)
            return 0;
        // faith
        // if wt[n] <= W then add or not add
        if (wt[n - 1] <= W)
            return Math.max(val[n - 1] + knapSack(wt, val, W - val[n - 1], n - 1), knapSack(wt, val, W, n - 1));
        else
            return knapSack(wt, val, W, n - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wt = new int[n];
        String[] weights = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            wt[i] = Integer.parseInt(weights[i]);
        int[] val = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            val[i] = Integer.parseInt(values[i]);
        int W = Integer.parseInt(br.readLine());

        int maxProfit = knapSack(wt, val, W, n - 1);
        System.out.println(maxProfit);
    }
}