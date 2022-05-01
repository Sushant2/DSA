import java.io.*;
import java.util.*;

//! Recursive relation : f(n) = f(n-1) + (n-1)*f(n-2) 

public class friendsPairing {

    // time compl : O(2^n) space : O(1) & recursion call stack space : O(n)
    public static int recursive(int n) {
        // when there is only 1 friend
        if (n <= 1)
            return 1;
        int solo = recursive(n - 1);
        int pair = recursive(n - 2) * (n - 1);
        return solo + pair;
    }

    // time compl : O(n), space comp : O(n)
    public static int memoized(int n, int[] qb) {
        if (n <= 1)
            return 1;
        if (qb[n] != -1)
            return qb[n];
        int solo = memoized(n - 1, qb);
        int pair = memoized(n - 2, qb) * (n - 1);
        int ways = solo + pair;
        qb[n] = ways;
        return ways;
    }



    public static int findPairingWays(int n) {
        // return recursive(n);
        int[] qb = new int[n + 1];
        Arrays.fill(qb, -1);
        qb[0] = qb[1] = 1;
        // return memoized(n, qb);
        return tabulation(n);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int totalWays = findPairingWays(n);
        System.out.println(totalWays);
    }
}
