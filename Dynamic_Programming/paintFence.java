import java.util.*;
import java.io.*;

public class paintFence {

    // time compl : O(n), space comp: O(n)
    public static int tabulation(int n, int k) {
        // edge case
        if (n == 0 || k == 0)
            return 0;
        if (n == 1)
            return k;
        // step1: create storage & assign meaning to cells
        long[] dp = new long[n];
        // meaning :
        // dp[i] will store total no. of ways till ith fence when last 2 fences have
        // same color and different both
        // ?initialization
        dp[0] = k; // only one fence
        // ! it's come from practice that, to solve this problem we are looking for last
        // 2 fences, & from combinations & permutaion
        // ? so we deduced this formula for same & difference, by DRY RUN
        long same = k * 1; // when last two fences have same color
        long diff = k * (k - 1); // when last two fences have different color
        dp[1] = same + diff;
        for (int i = 2; i < n; i++) {
            same = diff * 1;
            diff = dp[i - 1] * (k - 1);
            dp[i] = same + diff;
        }
        return (int) dp[n - 1];
    }

    // we can see there's no need of storing total ways, we can take a variable
    // insted, & upadte it, after every iteration ,as we just required last total
    // count
    // time compl : O(n), space compl : O(1)
    public static int tabulationWithSpaceOpt(int n, int k) {
        long same = k * 1;
        long diff = k * (k - 1);
        long total = same + diff;
        for (int i = 2; i < n; i++) {
            same = diff * 1;
            diff = total * (k - 1);
            total = same + diff;
        }
        return (int) total;
    }

    public static int findWaysToPaintFence(int n, int k) {
        // return tabulation(n, k);
        return tabulationWithSpaceOpt(n, k);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(); // no. of fences
        int k = scn.nextInt(); // no. of colors
        int totalWays = findWaysToPaintFence(n, k);
        System.out.println(totalWays);
    }
}
