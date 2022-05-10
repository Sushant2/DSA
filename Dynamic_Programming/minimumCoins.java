import java.util.*;
import java.io.*;

public class minimumCoins {

    public static int recursive(int[] arr, int ind, int target) {
        // base condition
        if (ind == 0) {
            if (target % arr[0] == 0)
                return target / arr[0];
            else
                return (int) Math.pow(10, 9);
        }

        // if not taking then count = 0, & if taking that coin, adding 1 to the count
        int notTake = 0 + recursive(arr, ind - 1, target);
        int take = (int) Math.pow(10, 9);
        if (arr[ind] <= target)
            take = 1 + recursive(arr, ind, target - arr[ind]);

        return Math.min(notTake, take);
    }

    public static int findMinCoins(int[] arr, int n, int target) {
        return recursive(arr, n - 1, target);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int target = scn.nextInt();
        int x = findMinCoins(arr, n, target);
        System.out.println(x);
    }
}