
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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // int paths = climbStairs(n);
        int paths = climbStairsMemoized(n, new int[n + 1]);
        System.out.println(paths);
    }
}