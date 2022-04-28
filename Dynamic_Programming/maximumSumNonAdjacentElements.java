import java.util.*;
import java.io.*;
//!LEETCODE : 198

public class maximumSumNonAdjacentElements {

    // little bit more code recursive sol :
    // ! time comp : O(2^n), space comp : O(1), or O(n) for recursion calls tack
    public static int recursive(int[] arr, int i, int n, int prev) {
        // base case
        if (i == n)
            return 0;
        // recur by excluding the current element
        int excl = recursive(arr, i + 1, n, prev);

        int incl = 0;
        // include curr ele only if, it's adjacent to the previous element
        if (i != prev + 1)
            incl = recursive(arr, i + 1, n, i) + arr[i];
        return Math.max(incl, excl);
    }

    public static int recursiveSimpleCode(int[] arr, int n) {
        // base case
        if (n < 0)
            return 0;
        int incl = recursiveSimpleCode(arr, n - 2) + arr[n];
        int excl = recursiveSimpleCode(arr, n - 1);
        return Math.max(incl, excl);
    }

    // time comp : O(n), space comp : O(n)
    public static int memoized(int[] arr, int n, int[] qb) {
        if (n < 0)
            return 0;

        if (qb[n] != -1)
            return qb[n];
        int incl = memoized(arr, n - 2, qb) + arr[n];
        int excl = memoized(arr, n - 1, qb);
        int result = Math.max(incl, excl);
        qb[n] = result;
        return result;
    }

    public static int tabulation(int[] arr, int n) {
        // step 1 : create storage & assign meaning to cells
        // meaning : dp[0][i] stores maximum subsequencessum till ith index with arr[i]
        // included
        // where as, dp[1][i] stores maximum subsequences till ith index with arr[i]
        // excluded
        int[][] dp = new int[2][n];
        // initialise
        dp[0][0] = arr[0]; // incl
        dp[1][0] = 0; // excl

        // step2 :direction of problem - small : dp[0][0], large dp[1][n]
        //step3 : travel & solve
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[1][i - 1] + arr[i];
            dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);
        }
        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }

    public static int maximumSumNonAdjacentELe(int[] arr, int n) {
        // return recursive(arr, 0, n, Integer.MIN_VALUE);
        // return recursiveSimpleCode(arr, n - 1);
        int[] qb = new int[n + 1];
        Arrays.fill(qb, -1);
        // return memoized(arr, n - 1, qb);
        return tabulation(arr, n);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();

        int maxSum = maximumSumNonAdjacentELe(arr, n);
        System.out.println(maxSum);
    }
}
