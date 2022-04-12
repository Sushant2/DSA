import java.io.*;
import java.util.*;

public class sortKSortedArray {

    // !BRTUE FORCE - O(nlogn)
    // just use any sorting algo
    public static void sortKSorted(int[] arr, int k) {
        Arrays.sort(arr);
        for (int val : arr)
            System.out.print(val + " ");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(values[i]);
        int k = Integer.parseInt(br.readLine());
        sortKSorted(arr, k);
    }
}
