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

    // ? if k = 0, that means input array is already sorted
    // ! OPTIMISED Approach - O(nlogk)
    // k+1 size ka priority queue/min heap with initial k+1 elements
    // ? why k+1 check notes
    // remove minimum/top
    // add next ele
    // remove minimum/top
    // add next ele
    // repeat the same

    public static void sortKSortedOptimised(int[] arr, int k) {
        // min heap/priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++)
            pq.add(arr[i]);
        for (int i = k; i < arr.length; i++) {
            pq.add(arr[i]);
            System.out.print(pq.remove() + " ");
        }

        // remaining element in PQ
        while (!pq.isEmpty())
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
        // sortKSorted(arr, k);
        sortKSortedOptimised(arr, k);
    }
}
