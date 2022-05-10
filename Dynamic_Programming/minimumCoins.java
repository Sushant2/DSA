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

    public static int findMinCoins(int[] arr, int n, int target) {
        // return recursive(arr, n - 1, target);
        int[][] qb = new int[n][target + 1];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        return memoization(arr, n - 1, target, qb);
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