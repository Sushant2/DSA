import java.io.*;
import java.util.*;
//given a floor 2xn, & infinite no. of 2x1.how many ways to fill this floor using tiles

public class tilingWithDominoes {

    private static int tabulation(int n) {
        // step1: create storage & assign meaning
        int[] dp = new int[n + 1];
        // meaning : dp[i] will storage the total ways to tile up i tiles on the floor
        // step2: direction of problem, small prblm to fill only floor of 1 tile, large
        // prlbm to fill floor of n size
        // !initliaze
        // to fill one tile of floor, there's only 1 way
        // to fill 2 tiles of floor, there's only 2 ways - putting both tiles
        // vertically, or horizontally
        dp[1] = 1;
        dp[2] = 2;

        // step3: travel & solve
        for (int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

    public static int tileUp(int n) {
        return tabulation(n);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int totalWays = tileUp(n);
        System.out.println(totalWays);
    }
}
