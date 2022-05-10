import java.util.*;
import java.io.*;

public class minimumCoins {

    // time compl : >>O(2^n) or exponential, space compl : >>O(n) or O(target)
    public static int recursive(int[] arr, int ind, int target) {
        // base condition
        if (ind == 0) {
            if (target % arr[0] == 0)
                return target / arr[0];
            else
                return (int) Math.pow(10, 9);
        }

        // if not taking then count = 0, & if taking that coin, adding 1 to the count
        int notTake = 0 + recursive(arr, ind - 1, target);
        int take = (int) Math.pow(10, 9);
        if (arr[ind] <= target)
            take = 1 + recursive(arr, ind, target - arr[ind]);

        return Math.min(notTake, take);
    }

    // time compl : O(n*target) or space compl : (n*target) + O(n) for recursion
    // call stack
    public static int memoization(int[] arr, int ind, int target, int[][] qb) {
        // base condition
        if (ind == 0) {
            if (target % arr[0] == 0)
                return target / arr[0];
            else
                return (int) Math.pow(10, 9);
        }
        if (qb[ind][target] != -1)
            return qb[ind][target];

        // if not taking then count = 0, & if taking that coin, adding 1 to the count
        int notTake = 0 + memoization(arr, ind - 1, target, qb);
        int take = (int) Math.pow(10, 9);
        if (arr[ind] <= target)
            take = 1 + memoization(arr, ind, target - arr[ind], qb);

        return qb[ind][target] = Math.min(notTake, take);
    }

    // time compl : O(n*target) or space compl : O(n*target)
    public static int tabulation(int[] arr, int target) {
        // step1: base case to initialization
        // step2: parameters that are changing :ind, target
        // write in opposite fashion of recursion
        // step3: copy the recurrence
        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        for (int tar = 0; tar <= target; tar++) {
            if (tar % arr[0] == 0)
                dp[0][tar] = tar / arr[0];
            else
                dp[0][tar] = (int) Math.pow(10, 9);
        }
        for (int ind = 1; ind < n; ind++) {
            for (int tar = 0; tar <= target; tar++) {
                int notTake = 0 + dp[ind - 1][tar];
                int take = (int) Math.pow(10, 9);
                if (arr[ind] <= tar)
                    take = 1 + dp[ind][tar - arr[ind]];
                dp[ind][tar] = Math.min(take, notTake);
            }
        }
        int ans = dp[n - 1][target];
        if (ans >= (int) Math.pow(10, 9))
            return -1;
        return ans;
    }

    // time compl : O(n*target) or space compl : O(target)
    public static int tabulationSpaceOpt(int[] arr, int target) {
        int n = arr.length;
        int[] prev = new int[target + 1];
        int[] curr = new int[target + 1];
        for (int tar = 0; tar <= target; tar++) {
            if (tar % arr[0] == 0)
                prev[tar] = tar / arr[0];
            else
                prev[tar] = (int) Math.pow(10, 9);
        }
        for (int ind = 1; ind < n; ind++) {
            for (int tar = 0; tar <= target; tar++) {
                int notTake = 0 + prev[tar];
                int take = (int) Math.pow(10, 9);
                if (arr[ind] <= tar)
                    take = 1 + curr[tar - arr[ind]];
                curr[tar] = Math.min(take, notTake);
            }
            prev = curr;
        }
        int ans = prev[target];
        if (ans >= (int) Math.pow(10, 9))
            return -1;
        return ans;
    }

    public static int findMinCoins(int[] arr, int n, int target) {
        // return recursive(arr, n - 1, target);
        int[][] qb = new int[n][target + 1];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        // return memoization(arr, n - 1, target, qb);
        // return tabulation(arr, target);
        return tabulationSpaceOpt(arr, target);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int target = scn.nextInt();
        int x = findMinCoins(arr, n, target);
        System.out.println(x);
    }
}