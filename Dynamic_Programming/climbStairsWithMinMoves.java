import java.util.*;
import java.io.*;

public class climbStairsWithMinMoves {

    public static int recursive(int i, int n, int[] arr) {
        if (i > n)
            return Integer.MAX_VALUE;
        if (i == n)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= arr[i]; j++) {
            int ans = recursive(i + j, n, arr);
            // jab koi na koi move aa rha ho tab hi min check krenge
            if (ans != Integer.MAX_VALUE)
                min = Math.min(min, ans + 1);
        }
        return min;
    }

    public static int climbStairs(int n, int[] arr) {
        return recursive(0, n, arr);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int minMoves = climbStairs(n, arr);
        System.out.println(minMoves);
    }
}
