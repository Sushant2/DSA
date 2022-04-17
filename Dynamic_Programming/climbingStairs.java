
//similar to climbing stairs paths - here we're just counting all paths, not printing them
import java.io.*;
import java.util.*;

public class climbingStairs {

    public static int climbStairs(int n) {
        // base condition
        if (n == 0)
            // 0 se 0, ek path
            return 1;
        if (n < 0)
            return 0;
        int nm1 = climbStairs(n - 1);
        int nm2 = climbStairs(n - 2);
        int nm3 = climbStairs(n - 3);

        int allPaths = nm1 + nm2 + nm3;
        return allPaths;
    }

    // now we can see overlapping & we're solving the same problem again and again
    // ! MEMOIZED
    public static int climbStairsMemoized(int n, int[] qb) {
        // base case
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        // if solved problem already present in the question bank
        if (qb[n] != 0)
            return qb[n];

        int nm1 = climbStairsMemoized(n - 1, qb);
        int nm2 = climbStairsMemoized(n - 2, qb);
        int nm3 = climbStairsMemoized(n - 3, qb);
        int allPaths = nm1 + nm2 + nm3;
        // storing answer in qb
        qb[n] = allPaths;
        return allPaths;
    }

    // ! TABULATION - ITERATION
    // tabulation steps -
    // 1. create storage & assign meaning to each cell(call ko menaing - cell m ye
    // store krnge ki us cell se zero/0 jane k kitne raste h)
    // 2. identify direction of problem(choti prblm kis taraf, badi prblm kis taraf)
    // 0->0 choti prblm, 6->0 badi prblm
    // 3. travel & solve
    public static int climbStairsTabulation(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // 0->0 jane ka 1 raste h
        for (int i = 1; i <= n; i++) {
            if (i == 1)
                // i=1, ke lie dp[i-2] & dp[i-3] out of table chale jayenge
                dp[i] = dp[i - 1];
            else if (i == 2)
                // i=2, ke lie dp[i-3] out of table chale jayenge
                dp[i] = dp[i - 1] + dp[i - 2];
            else
                // i is 3 onwards
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // int paths = climbStairs(n);
        // int paths = climbStairsMemoized(n, new int[n + 1]);
        int paths = climbStairsTabulation(n);
        System.out.println(paths);
    }
}