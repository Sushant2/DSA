import java.io.*;
import java.util.*;

//DEDUCED RECURRENCE RELATION : fn(n,k) = fn(n-1,k-1) + fn(n-1,k)*k;

public class partitionIntoSubsets {

    // time comp :O(2^n),
    public static int recursive(int n, int k) {
        // base case
        if (n == 0 || k == 0 || n < k)
            return 0;
        if (n == k || k == 1)
            return 1;

        return recursive(n - 1, k - 1) + recursive(n - 1, k) * k;
    }

    public static int findWays(int n, int k) {
        return recursive(n, k);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt(); // no. of people
        int k = scn.nextInt(); // no. of teams
        int totalWays = findWays(n, k);
        System.out.println(totalWays);

    }
}
