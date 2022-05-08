import java.io.*;
import java.util.*;

public class minimumPlatform {
    public static int findMaxPlatform(int[] arr, int[] dep, int n) {
        // bruteForce - time comp : O(n^2), space comp : O(1)
        int plat_needed = 1, result = 1;
        int i = 1, j = 0;
        for (i = 0; i < n; i++) {
            // minimum platform
            plat_needed = 1;
            for (j = i + 1; j < n; j++) {
                // check for overlap
                if (Math.max(arr[i], arr[j]) <= Math.min(dep[i], dep[j]))
                    plat_needed++;
            }
            result = Math.max(result, plat_needed);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int[] dep = new int[n];
        for (int i = 0; i < n; i++)
            dep[i] = scn.nextInt();
        int maxPlatform = findMaxPlatform(arr, dep, n);
        System.out.println(maxPlatform);
    }
}
