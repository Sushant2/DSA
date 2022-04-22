import java.io.*;
import java.util.*;

public class coinChangePermutation {

    // find count of permutaions of given amount
    // ! TIME COMPLEXITY - O(n^T) = n is no.of coins & t is target - exponential
    // time compl
    public static int recursive(int[] arr, int amt) {
        // +base case
        if (amt == 0)
            return 1;
        if (amt < 0)
            return 0;
        int res = 0;

        // for every coin in array - At each index, if the element is less than target,
        // then we can always form a combination by picking the current element.
        for (int coin : arr)
            res += recursive(arr, amt - coin);

        return res;

    }

    public static int coinChangePerm(int[] arr, int n, int amt) {
        return recursive(arr, amt);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int amt = scn.nextInt();
        int totalWays = coinChangePerm(arr, n, amt);
        System.out.println(totalWays);
    }
}
