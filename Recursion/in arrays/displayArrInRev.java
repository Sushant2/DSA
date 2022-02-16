import java.io.*;
import java.util.*;

public class displayArrInRev {
    public static void disArrRev(int[] arr, int idx) {
        // base case
        if (idx == arr.length)
            return;
        // faith
        disArrRev(arr, idx + 1);
        // meeting expec
        System.out.print(arr[idx] + " ");
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        disArrRev(arr, 0);
        scn.close();
    }
}
