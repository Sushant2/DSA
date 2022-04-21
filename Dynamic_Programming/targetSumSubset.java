import java.io.*;
import java.util.*;

public class targetSumSubset {

    public static boolean recursive(int[] arr, int sum, int idx) {
        // +ve base case
        if (sum == 0)
            return true;
        // -ve base case
        if (idx == arr.length)
            return false;

        boolean left = recursive(arr, sum - arr[idx], idx + 1);
        boolean right = recursive(arr, sum, idx + 1);

        return left || right;
    }

    public static int memoized(int[] arr, int sum, int idx, int[][] qb) {
        // +ve base case
        if (sum == 0)
            return 1;
        // -ve base case
        if (idx == arr.length || sum < 0)
            return 0;

        // if answer to that question is already present in qb
        if (qb[idx][sum] != -1)
            return qb[idx][sum];

        if (memoized(arr, sum - arr[idx], idx + 1, qb) != 0 || memoized(arr, sum, idx + 1, qb) != 0)
            return qb[idx][sum] = 1;
        else
            return qb[idx][sum] = 0;
    }

    public static boolean tabulation(int[] arr, int sum) {
        // step 1. create storage & assign meaning
        boolean[][] dp = new boolean[arr.length + 1][sum + 1];
        // meaning of cell - in dp[i][j] - that array of size i with i elements having
        // sum equals to j
        // step 2. direction of problem - choti problem - dp[0][0], baadi problem -
        // dp[n][sum]
        // step 3. travel & solve
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                // first cell
                if (i == 0 && j == 0)
                    dp[i][j] = true;
                // first row
                else if (i == 0)
                    dp[i][j] = false;
                // first column
                else if (j == 0)
                    dp[i][j] = true;
                // rest of it
                else {
                    // dp[i][j] - if ith batting nhi krta
                    // check kya, i-1 players ne run banaliye
                    if (dp[i - 1][j] == true)
                        dp[i][j] = true;
                    // if i-1 players run nhi banapaye to, batting krenge
                    else {
                        // for batting - j >= ith value
                        // dp ka index i = then array ka index i-1
                        int val = arr[i - 1];
                        if (j >= val) {
                            // agar i ne batting krli & jo run bache, wo check kro, kya wo i-1 players ne
                            // score kie hai?
                            if (dp[i - 1][j - val] == true)
                                dp[i][j] = true;
                        }
                    }
                }
            }
        }
        return dp[arr.length][sum];
    }

    public static boolean findTargetSubset(int[] arr, int sum) {
        // return recursive(arr, sum, 0);
        // int[][] qb = new int[arr.length + 1][sum + 1];
        // for (int i = 0; i < arr.length + 1; i++)
        // for (int j = 0; j < sum + 1; j++)
        // qb[i][j] = -1;

        // int ans = memoized(arr, sum, 0, qb);
        // return (ans == 1) ? true : false;

        return tabulation(arr, sum);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int target = scn.nextInt();
        boolean ans = findTargetSubset(arr, target);
        System.out.println(ans);
    }
}
