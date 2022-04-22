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

        if (qb[n][amt] != -1)
            return qb[n][amt];

        // include that coin
        if (arr[n - 1] <= amt)
            return qb[n][amt] = memoized(arr, n, amt - arr[n - 1], qb) + memoized(arr, n - 1, amt, qb);
        else
            // exclude that coin
            return qb[n][amt] = memoized(arr, n - 1, amt, qb);
    }

    public static int tabulation(int[] arr, int n, int amt) {
        // step 1. create storage & assign meaning to cells
        int[] dp = new int[amt + 1];
        // meaning of cell - e.g., for dp[j] = j ko pay krne k kitne combinations hai
        // using these coins
        // ! 0(zero) ko pay krne ka hamesha ek rasta hoga - ki kuch pay mat kro
        dp[0] = 1;
        // step 2. direction of problem - choti problem - dp[0], badi problem dp[n]
        // step 2. travel & solve
        // e.g. = arr = {2,3,5} , amt = 7
        // ! outer loop (coins wala)
        // hme har coins ka effect dalna hai har amount k lie - pahle sare dp table m, 2
        // ka loop lagaenge then 3, & then 5 & so on... & isse we're ensuring that
        // combinations bani rhe, pahle sare 2, then 3, then 5
        // ! we'll start inner loop from arr[i], cos usse pahle hum kuch pay hi nhi
        // krpayemge

        // Pick all coins one by one and update the table[]
        // values after the index greater than or equal to
        // the value of the picked coin
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i]; j < dp.length; j++) {
                dp[j] += dp[j - arr[i]];
            }
        }
        return dp[amt];
    }

    public static int coinChange(int[] arr, int n, int amt) {
        // return recursive(arr, n, amt);
        int[][] qb = new int[n + 1][amt + 1];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        // return memoized(arr, n, amt, qb);
        return tabulation(arr, n, amt);
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