import java.io.*;
import java.util.*;
//count all binary strings of length n w/o consecutive 0s

public class countBinaryStrings {

    // time comp : O(2^n), space comp : O(1)
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

    // time comp : O(n), space comp : O(n) =~ O(n*2)

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

    public static int tabulation(int n) {
        // using two 1-d arrays or a 2d arrays
        // step 1 : create storage & assign meaning to cells
        // dp0 : arrays having count of numbers ending with 0
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];

        // by default dp0[0] & dp1[0] is zero, & dp0[1] ,dp1[1] are also trivial cases,
        // so we prefilled
        dp0[1] = 1;
        dp1[1] = 1;

        // meaning cell : dp0[i] will store count of number of binary numbers ending
        // with 0
        // dp1[i] will store count of number of binary numbers ending with 1

        // step 2 :direction of problem choti prblm dp0[0] dp1[0] & badi problem dp0[n],
        // dp1[n]

        // step 3: travel & solve
        for (int i = 2; i <= n; i++) {
            dp0[i] = dp1[i - 1];
            dp1[i] = dp0[i - 1] + dp1[i - 1];
        }
        return dp0[n] + dp1[n];
    }

    public static int tabulationOpti(int n) {
        int oldCZeroes = 1;
        int oldCOnes = 1;

        for (int i = 2; i <= n; i++) {
            int newCZeroes = oldCOnes;
            int newCOnes = oldCOnes + oldCZeroes;
            oldCOnes = newCOnes;
            oldCZeroes = newCZeroes;
        }

        return oldCOnes + oldCZeroes;
    }

    public static int countBStrings(int n) {
        // initially last digit taken as 1, cos it'll give 2 choices
        // return recursive(n, 1);
        int[][] qb = new int[2][n];
        for (int[] x : qb)
            Arrays.fill(x, -1);

        qb[0][0] = 1;
        qb[1][0] = 1;

        // return memoized(n - 1, 1, qb) + memoized(n - 1, 0, qb);

        // return tabulation(n);
        return tabulationOpti(n);

    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int count = countBStrings(n);
        System.out.println(count);
    }
}
