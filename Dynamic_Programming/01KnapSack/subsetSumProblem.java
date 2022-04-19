import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class subsetSumProblem {

    public static boolean recursive(int n, int sum, int[] arr) {
        // -ve base case
        if (sum == 0)
            return true;
        // +ve base case
        if (sum > 0 && n == 0)
            return false;

        // ! ignore, if last element is greater than sum
        if (arr[n - 1] > sum)
            return recursive(n - 1, sum, arr);
        System.out.println("Hello" + n);
        boolean left = recursive(n - 1, sum - arr[n - 1], arr);
        boolean right = recursive(n - 1, sum, arr);

        return left || right;
    }

    public static int memoized(int n, int sum, int[] arr, int[][] qb) {
        // base case
        if (sum == 0)
            return 1;
        if (n == 0)
            return 0;
        // if answer to the problem already exist in the qb
        if (qb[n - 1][sum] != -1)
            return qb[n - 1][sum];
        // if last ele is greater than sum
        System.out.println("Hello " + n);
        if (arr[n - 1] > sum)
            return qb[n - 1][sum] = memoized(n - 1, sum, arr, qb);
        else {
            if (memoized(n - 1, sum - arr[n - 1], arr, qb) != 0 || memoized(n - 1, sum, arr, qb) != 0)
                return qb[n - 1][sum] = 1;
            else
                return qb[n - 1][sum] = 0;
        }
    }

    public static boolean tabulation(int n, int[] arr, int sum) {
        // step 1. create storage and assign meaning to cells
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // initlise it rows having all true & all columns having false, except dp[0][0]
        // will have true;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                if (i == 0)
                    dp[i][j] = false;
                if (j == 0)
                    dp[i][j] = true;
            }
        }
        // meaning of cell - dp[i][j] wil store that, for that n elements, the sum exist
        // or not, if there exists a subset from n elements then true, else false
        // step 2. direction of problem -
        // choti prblm - dp[0][0], badi problem dp[n][sum]
        // step 3 travel and solve
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][sum];
    }

    public static boolean findSubsetSum(int sum, int[] arr) {
        // return recursive(arr.length - 1, sum, arr);
        // int[][] qb = new int[arr.length + 1][sum + 1];
        // for (int i = 0; i < arr.length + 1; i++)
        // for (int j = 0; j < sum + 1; j++)
        // qb[i][j] = -1;
        // int ans = memoized(arr.length - 1, sum, arr, qb);
        // return ans == 1 ? true : false;

        return tabulation(arr.length, arr, sum);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int sum = scn.nextInt();

        boolean ans = findSubsetSum(sum, arr);
        if (ans)
            System.out.println("Present!");
        else
            System.out.println("Not present!");
    }
}
