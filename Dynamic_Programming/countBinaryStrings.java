import java.io.*;
import java.util.*;
//count all binary strings of length n w/o consecutive 0s

public class countBinaryStrings {
    public static int recursive(int n, int last) {
        // base case
        if (n == 0)
            return 0;
        if (n == 1) {
            if (last == 1)
                return 2;
            if (last == 0)
                return 1;
        }
        if (last == 1)
            return recursive(n - 1, 1) + recursive(n - 1, 0);
        else
            return recursive(n - 1, 1);

    }

    public static int memoized(int n, int last, int[][] qb) {
        if (n == 0)
            return qb[last][n] = 0;
        if (n == 1) {
            if (last == 1)
                return qb[last][n] = 2;
            if (last == 0)
                return qb[last][n] = 1;
        }
        if (qb[last][n] != -1)
            return qb[last][n];

        if (last == 1)
            return qb[last][n] = memoized(n - 1, 1, qb) + memoized(n - 1, 0, qb);
        else
            return qb[last][n] = memoized(n - 1, 1, qb);
    }

    public static int countBStrings(int n) {
        // initially last digit taken as 1, cos it'll give 2 choices
        // return recursive(n, 1);
        int[][] qb = new int[2][n];
        for (int[] x : qb)
            Arrays.fill(x, -1);

        qb[0][0] = 1;
        qb[1][0] = 1;

        return memoized(n - 1, 1, qb) + memoized(n - 1, 0, qb);

    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int count = countBStrings(n);
        System.out.println(count);
    }
}
