import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.io.*;

public class paintHouse {

    // time comp : O(n), space comp: O(n)
    public static int tabulation(long[][] arr, int n) {
        // step 1: create storage & assign meaning to cells
        long[][] dp = new long[arr.length][arr[0].length];
        // meaning of cells : dp[i][1] will store minimum cost of painting 'i' houses
        // such that no two adjacent houses have same colour & the ending house is of
        // '1'th color
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        // step 2 : direction of problem
        // small problem : dp[0][0], large problem : dp[n][2]

        // step 3: travel & solve
        for (int i = 1; i < arr.length; i++) {
            // red
            dp[i][0] = arr[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            // blue
            dp[i][1] = arr[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            // green
            dp[i][2] = arr[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        return (int) Math.min(dp[n - 1][0], Math.min(dp[n - 1][2], dp[n - 1][2]));
    }

    // time comp : O(n), space comp : O(1)
    public static int tabulationSpaceOpt(long[][] arr, int n) {
        long red = arr[0][0];
        long blue = arr[0][1];
        long green = arr[0][2];

        for (int i = 1; i < arr.length; i++) {
            long newRed = arr[i][0] + Math.min(green, blue);
            long newBlue = arr[i][1] + Math.min(red, green);
            long newGreen = arr[i][2] + Math.min(red, blue);

            red = newRed;
            blue = newBlue;
            green = newGreen;
        }
        return (int) Math.min(red, Math.min(blue, green));
    }

    public static int costingPaintHouse(long[][] arr) {
        // return tabulation(arr, arr.length);
        return tabulationSpaceOpt(arr, arr.length);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        long[][] arr = new long[n][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                arr[i][j] = scn.nextInt();

        int minCost = costingPaintHouse(arr);
        System.out.println(minCost);
    }
}
