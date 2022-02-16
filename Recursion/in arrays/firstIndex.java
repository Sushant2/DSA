import java.io.*;
import java.util.*;

public class firstIndex {
    public static int FI(int[] arr, int idx, int x) {
        // base case
        if (idx == arr.length)
            return -1;
        // meeting expec
        if (arr[idx] == x)
            return idx;
        else
            // faith
            return FI(arr, idx + 1, x);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int x = scn.nextInt();
        System.out.println(FI(arr, 0, x));
        scn.close();
    }
}
