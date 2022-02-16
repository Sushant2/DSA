import java.io.*;
import java.util.*;

public class displayArr {
    public static void disArr(int[] arr, int idx) {
        // base case
        if (idx == arr.length)
            return;
        // meeting expec
        System.out.print(arr[idx] + " ");
        // faith
        disArr(arr, idx + 1);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        disArr(arr, 0);
        scn.close();
    }
}
