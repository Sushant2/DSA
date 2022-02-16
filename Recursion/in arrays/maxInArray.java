import java.util.*;
import java.io.*;

public class maxInArray {
    public static int maxInArr(int[] arr, int idx) {
        // base case
        if (idx == arr.length)
            return Integer.MIN_VALUE;
        // faith
        int smallMax = maxInArr(arr, idx + 1);
        // meeting expec
        return Math.max(smallMax, arr[idx]);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        System.out.println(maxInArr(arr, 0));
        scn.close();
    }
}
