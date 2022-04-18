import java.io.*;
import java.util.*;

public class climbStairsWithVarJump {

    public static int recursive(int i, int n, int[] arr) {
        // base case
        if (i > n)
            return 0;
        if (i == n)
            return 1;
        System.out.println("Hello " + i);
        int paths = 0;
        for (int j = 1; j <= arr[i]; j++) {
            paths += recursive(i + j, n, arr);
        }
        return paths;
    }

    public static int memoization(int i, int n, int[] arr, int[] qb) {
        // base case
        if (i > n)
            return 0;
        if (i == n)
            return 1;
        if (qb[i] != 0)
            return qb[i];
        System.out.println("Hello " + i);

        int paths = 0;
        for (int j = 1; j <= arr[i]; j++)
            paths += memoization(i + j, n, arr, qb);
        qb[i] = paths;
        return paths;
    }

    public static int climbStairsJumps(int n, int[] arr) {
        // return recursive(0, n, arr);
        return memoization(0, n, arr, new int[n + 1]);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int allPaths = climbStairsJumps(n, arr);
        System.out.println(allPaths);
    }
}
