import java.util.*;
import java.io.*;

public class kLargestElement {

    // ! BRTUE FORCE Approach - O(nlogn)
    // sort the array & print/return last k elements
    public static void kLargest(int[] arr, int k) {
        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= arr.length - k; i--)
            System.out.println(arr[i]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(values[i]);
        int k = Integer.parseInt(br.readLine());
        kLargest(arr, k);
        // kLargestMaxHeap(arr, k);
    }
}
