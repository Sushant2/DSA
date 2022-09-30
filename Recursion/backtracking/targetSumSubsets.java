import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int targetSum = scn.nextInt();
        printTargetSumSubsets(arr, 0, "", targetSum);
    }

    // set is the subset
    // sos is sum of subset
    // tar is target
    public static void printTargetSumSubsets(int[] arr, int idx, String set, int targetSum) {
        // base case
        if (idx == arr.length) {
            if (targetSum == 0) {
                System.out.println(set + ".");
            }
            return;
        }
        // pruning
        if (targetSum < 0) {
            return;
        }
        // yes faith
        printTargetSumSubsets(arr, idx + 1, set + arr[idx] + ", ", targetSum - arr[idx]);
        // no faith
        printTargetSumSubsets(arr, idx + 1, set, targetSum);

    }

}