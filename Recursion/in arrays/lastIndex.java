import java.io.*;
import java.util.*;

public class lastIndex {
    public static int LI(int[] arr, int idx, int x) {
        // base case
        if (idx < 0)
            return -1;
        // meeting expec
        if (arr[idx] == x)
            return idx;
        else
            // faith
            return LI(arr, idx - 1, x);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int x = scn.nextInt();
        System.out.println(LI(arr, arr.length - 1, x));
        scn.close();
    }
}
