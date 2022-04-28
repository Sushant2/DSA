import java.util.*;
import java.io.*;
//!LEETCODE : 198

public class maximumSumNonAdjacentElements {

    // little bit more code recursive sol :
    // ! time comp : O(2^n), space comp : O(1), or O(n) for recursion calls tack
    public static int recursive(int[] arr, int i, int n, int prev) {
        // base case
        if (i == n)
            return 0;
        // recur by excluding the current element
        int excl = recursive(arr, i + 1, n, prev);

        int incl = 0;
        // include curr ele only if, it's adjacent to the previous element
        if (i != prev + 1)
            incl = recursive(arr, i + 1, n, i) + arr[i];
        return Math.max(incl, excl);
    }

    public static int maximumSumNonAdjacentELe(int[] arr, int n) {
        return recursive(arr, 0, n, Integer.MIN_VALUE);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();

        int maxSum = maximumSumNonAdjacentELe(arr, n);
        System.out.println(maxSum);
    }
}
