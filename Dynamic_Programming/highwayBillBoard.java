import java.io.*;
import java.util.*;

public class highwayBillBoard {

    public static int findProfit(int m, int[] x, int[] rev, int t) {
        // first approach - based on no. of boards - same as LIS(least increasing
        // subsequence)
        // time comp : O(n^2), space comp : O(n)
        int[] dp = new int[x.length + 1];
        int ans = 0; // overall ans
        dp[0] = rev[0];
        for (int i = 1; i < x.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                int dist = x[i] - x[j];
                if (dist > t) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = rev[i] + max;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // second approach - based on no of miles
    // time compl : O(m), space comp : O(m)
    public static int findProfit2(int m, int[] x, int[] rev, int t) {
        // hashmap to store, revenue of that ith board, with it's mile
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < x.length; i++)
            map.put(x[i], rev[i]);
        int[] dp = new int[m + 1];
        dp[0] = 0;
        for (int i = 1; i <= m; i++) {
            if (map.containsKey(i) == false)
                dp[i] = dp[i - 1];
            else {
                // 2 options
                int boardNotInstall = dp[i - 1];
                int boardInstall = map.get(i);
                if (i >= t + 1)
                    boardInstall += dp[i - t - 1];
                dp[i] = Math.max(boardInstall, boardNotInstall);
            }
        }
        return dp[m];
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int m = scn.nextInt(); // m miles
        int n = scn.nextInt(); // n billboards
        int[] x = new int[n];
        for (int i = 0; i < n; i++)
            x[i] = scn.nextInt();
        int[] rev = new int[n];
        for (int i = 0; i < n; i++)
            rev[i] = scn.nextInt();
        int t = scn.nextInt(); // t gap between two billboards
        // int maxProfit = findProfit(m, x, rev, t);
        int maxProfit = findProfit2(m, x, rev, t);
        System.out.println(maxProfit);
        scn.close();
    }
}
