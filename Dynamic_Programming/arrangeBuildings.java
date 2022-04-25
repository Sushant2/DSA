import java.io.*;
import java.lang.reflect.Member;
import java.util.*;

public class arrangeBuildings {

    public static int recursive(int n, int last) {
        if (n == 0)
            return 0;
        if (n == 1) {
            if (last == 0)
                return 2;
            if (last == 1)
                return 1;
        }

        if (last == 0) {
            return recursive(n - 1, 0) + recursive(n - 1, 1);
        } else
            return recursive(n - 1, 0);
    }

    public static int memoized(int n, int last, int[][] qb) {
        if (n == 0)
            return qb[last][n] = 0;
        if (n == 1) {
            if (last == 0)
                return qb[last][n] = 2;
            if (last == 1)
                return qb[last][n] = 1;
        }
        // if question to that answer is already present in qb
        if (qb[last][n] != -1)
            return qb[last][n];
        if (last == 0)
            return qb[last][n] = memoized(n - 1, 0, qb) + memoized(n - 1, 1, qb);
        else
            return qb[last][n] = memoized(n - 1, 0, qb);
    }

    public static long tabulation(int n) {
        long oldB = 1;
        long oldS = 1;
        for (int i = 2; i <= n; i++) {
            long newB = oldS;
            long newS = oldB + oldS;

            oldS = newS;
            oldB = newB;
        }
        long total = oldS + oldB;
        return total * total;
    }

    public static long arrangement(int n) {
        // space means = 0, building means = 1
        // int ans = recursive(n, 0);
        int[][] qb = new int[2][n];
        for (int[] x : qb)
            Arrays.fill(x, -1);
        qb[0][0] = 1;
        qb[1][0] = 1;
        // int ans = memoized(n - 1, 0, qb) + memoized(n - 1, 1, qb);
        long ans = tabulation(n);
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        long ans = arrangement(n);
        System.out.println(ans);
    }
}
