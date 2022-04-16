import java.util.*;
import java.io.*;

public class _01knapsackMemoization {

    public static int knapSackMemoization(int[] wt, int[] val, int W, int n, int[][] qb) {
        // base case
        // if items = 0 & weight of bag = 0 then maxProfit is also 0
        if (W == 0 || n == 0)
            return 0;
        // if problem already exist in qb with solution, we'll directly return the ans
        if (qb[n][W] != 0)
            return qb[n][W];
        // faith
        // if wt[n] <= W then add or not add
        if (wt[n - 1] <= W) {
            qb[n][W] = Math.max(val[n - 1] + knapSackMemoization(wt, val, W - wt[n - 1], n - 1, qb),
                    knapSackMemoization(wt, val, W, n - 1, qb));
            return qb[n][W];
        } else {
            qb[n][W] = knapSackMemoization(wt, val, W, n - 1, qb);
            return qb[n][W];
        }
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

        int[][] qb = new int[n + 1][W + 1];

        int maxProfit = knapSackMemoization(wt, val, W, n, qb);
        System.out.println(maxProfit);
    }
}