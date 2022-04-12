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

    // ! Optimised Approach - use MaxHeap - O(nlogn + klogn) //insertion & k
    // elements remove
    // store every element in max heap & pop first k elements
    public static void kLargestMaxHeap(int[] arr, int k) {
        // maxheap priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int val : arr)
            pq.add(val);
        while (k-- > 0)
            System.out.print(pq.remove() + " ");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(values[i]);
        int k = Integer.parseInt(br.readLine());
        // kLargest(arr, k);
        kLargestMaxHeap(arr, k);
    }
}
