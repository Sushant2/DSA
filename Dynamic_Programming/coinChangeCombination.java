import java.io.*;
import java.util.*;

public class coinChangeCombination {

    public static int recursive(int[] arr, int n, int amt) {
        // +ve base case
        if (amt == 0)
            return 1;
        // -ve base case
        if (amt < 0)
            return 0;
        if (n <= 0 && amt >= 1)
            return 0;

        int includeCoin = recursive(arr, n, amt - arr[n - 1]);
        int excludeCoin = recursive(arr, n - 1, amt);

        return includeCoin + excludeCoin;
    }

    public static int memoized(int[] arr, int n, int amt, int[][] qb) {
        // +ve base case
        if (amt == 0)
            return qb[n][amt] = 1;
        if (amt < 0)
            return 0;
        if (n <= 0 && amt >= 1)
            return 0;

        // include that coin
        if (arr[n - 1] <= amt)
            return qb[n][amt] = memoized(arr, n, amt - arr[n - 1], qb) + memoized(arr, n - 1, amt, qb);
        else
            // exclude that coin
            return qb[n][amt] = memoized(arr, n - 1, amt, qb);
    }

    public static int coinChange(int[] arr, int n, int amt) {
        // return recursive(arr, n, amt);
        int[][] qb = new int[n + 1][amt + 1];
        return memoized(arr, n, amt, qb);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int amt = scn.nextInt();
        int totalWays = coinChange(arr, n, amt);
        System.out.println(totalWays);
    }
}