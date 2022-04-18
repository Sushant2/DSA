import java.io.*;
import java.util.*;

public class climbStairsWithVarJump {

    public static int recursive(int i, int n, int[] arr) {
        // base case
        if (i > n)
            return 0;
        if (i == n)
            return 1;
        int paths = 0;
        for (int j = 1; j <= arr[i]; j++) {
            paths += recursive(i + j, n, arr);
        }
        return paths;
    }

    public static int climbStairsJumps(int n, int[] arr) {
        return recursive(0, n, arr);
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
