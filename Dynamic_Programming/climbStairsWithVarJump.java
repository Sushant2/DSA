import java.io.*;
import java.util.*;

public class climbStairsWithVarJump {

    public static int recursive(int i, int n, int[] arr) {
        // base case
        if (i > n)
            return 0;
        if (i == n)
            return 1;
        int paths = 0;
        for (int j = 1; j <= arr[i]; j++) {
            paths += recursive(i + j, n, arr);
        }
        return paths;
    }

    public static int memoization(int i, int n, int[] arr, int[] qb) {
        // base case
        if (i > n)
            return 0;
        if (i == n)
            return 1;
        if (qb[i] != 0)
            return qb[i];

        int paths = 0;
        for (int j = 1; j <= arr[i]; j++)
            paths += memoization(i + j, n, arr, qb);
        qb[i] = paths;
        return paths;
    }

    public static int tabulation(int n, int[] arr) {
        // step 1 : create storage & assign meaning to cells
        int[] dp = new int[n + 1];
        // recursion/memoization base case is initilazation in tabulation
        dp[n] = 1;
        // step 2 : direction of problem
        // choti problem (n->n tho 1 path), badi problem(0->n then x paths)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= arr[i] && i + j < dp.length; j++) {
                dp[i] += dp[i + j];
            }
        }
        return dp[0];
    }

    public static int climbStairsJumps(int n, int[] arr) {
        // return recursive(0, n, arr);
        // return memoization(0, n, arr, new int[n + 1]);
        return tabulation(n, arr);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int allPaths = climbStairsJumps(n, arr);
        System.out.println(allPaths);
    }
}
