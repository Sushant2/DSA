import java.io.*;
import java.util.*;

public class allIndices {
    public static int[] allIndicesArr(int[] arr, int idx, int target, int counter) {
        // base case
        if (idx == arr.length) {
            int[] base = new int[counter];
            return base;
        }
        // meeting expec with faith
        if (arr[idx] == target) {
            // pre order
            int[] res = allIndicesArr(arr, idx + 1, target, counter + 1);
            // post order
            res[counter] = idx;
            return res;
        } else {
            int[] res = allIndicesArr(arr, idx + 1, target, counter);
            return res;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int target = scn.nextInt();
        int[] res = allIndicesArr(arr, 0, target, 0);
        for (int val : res) {
            System.out.print(val + " ");
        }
        scn.close();
    }
}