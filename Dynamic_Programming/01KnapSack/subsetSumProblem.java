import java.io.*;
import java.util.*;

public class subsetSumProblem {

    public static boolean recursive(int n, int sum, int[] arr) {
        // -ve base case
        if (sum == 0)
            return true;
        // +ve base case
        if (sum > 0 && n == 0)
            return false;

        // ! ignore, if last element is greater than sum
        if (arr[n - 1] > sum)
            return recursive(n - 1, sum, arr);
        boolean left = recursive(n - 1, sum - arr[n - 1], arr);
        boolean right = recursive(n - 1, sum, arr);

        return left || right;
    }

    public static boolean findSubsetSum(int sum, int[] arr) {
        return recursive(arr.length - 1, sum, arr);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int sum = scn.nextInt();

        boolean ans = findSubsetSum(sum, arr);
        if (ans)
            System.out.println("Present!");
        else
            System.out.println("Not present!");
    }
}
