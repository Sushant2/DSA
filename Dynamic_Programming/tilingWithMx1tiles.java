import java.util.*;
import java.io.*;

//given n & m, length & breadth of a floor(n*m)
//given inifinte supply of m*1 tiles
//print & calculate the no. of ways floor can be tiled using tiles
public class tilingWithMx1tiles {

    // time complexity : O(n), space complexity : O(n)
    public static int tabulation(int n, int m) {
        // step1: create storage & assign meaning to cells
        int[] dp = new int[n + 1];
        // dp[i] will store the no. of ways of tiling i tiles , where floor is n*m of
        // tile m*1
        // step2 : small problem, dp[1], large problem dp[n]
        // step3: travel & solve
        for (int i = 1; i <= n; i++) {
            // when length is less than breadth, means ther's only one to to tileup i'e,
            // vertically
            if (i < m)
                dp[i] = 1;
            // when length == breadth, there are 2 ways to tile up, horizontally &
            // vertically
            else if (i == m)
                dp[i] = 2;
            else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        return dp[n];
    }

    public static int tilingWays(int n, int m) {
        return tabulation(n, m);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int totalWays = tilingWays(n, m);
        System.out.println(totalWays);
    }
}
