import java.util.*;
import java.io.*;

//paint house many colors
public class paintHouse2 {

    // time complexity : O(n^3), space : O(n^2) for 2d matrix
    private static int tabulation(int[][] arr) {
        // step 1: create storage & assign meaning to cells
        int[][] dp = new int[arr.length][arr[0].length];
        for (int j = 0; j < arr[0].length; j++)
            dp[0][j] = arr[0][j];
        // meaning to cells : dp[i][j] means - ith house tak kitne cost lagegi, such
        // that no two consecutive house have same color & the ending house is painted
        // with color j

        // for each house starting from 2nd house
        for (int i = 1; i < dp.length; i++) {
            // for each colour
            for (int j = 0; j < dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                // sare colors par traverse to find "min" from the previous row
                for (int k = 0; k < dp[0].length; k++) {
                    if (k != j) {
                        if (dp[i - 1][k] < min)
                            min = dp[i - 1][k];
                    }
                }
                dp[i][j] = arr[i][j] + min;
            }
        }
        // ans is minimum of last row
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < dp[0].length; k++)
            if (dp[arr.length - 1][k] < min)
                min = dp[arr.length - 1][k];

        return min;
    }

    public static int paintHousemanyColours(int[][] arr) {
        return tabulation(arr);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(); // houses
        int m = scn.nextInt(); // colors
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                arr[i][j] = scn.nextInt();
        }
        int minCost = paintHousemanyColours(arr);
        System.out.println(minCost);
    }
}
