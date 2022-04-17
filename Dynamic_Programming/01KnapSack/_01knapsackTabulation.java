import java.util.*;
import java.io.*;

public class _01knapsackTabulation {

    public static int knapSackTabulation(int[] wt, int[] val, int W, int n) {
        // step 1. create storage & assign meaning to cells
        int[][] dp = new int[n + 1][W + 1];
        // initialize first row & first column with zero as base case was if(n==0 || W
        // == 0) return 0;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
            }
        }
        // step 2. direction of problem - from smaller to bigger
        // step 3. travel & solve
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if (wt[i - 1] <= j)
                    dp[i][j] = Math.max(val[i - 1] + dp[n - 1][j - wt[i - 1]], dp[n - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][W];
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
        int maxProfit = knapSackTabulation(wt, val, W, n);
        System.out.println(maxProfit);
    }
}
