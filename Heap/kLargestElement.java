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

    // ! Optimised Approach - use MaxHeap - O(nlogn + klogn)
    // ! nlong - insertion(can be done in (n) with effiecient constructor
    // ! klogn - k elements remove
    // store every element in max heap & pop first k elements
    public static void kLargestMaxHeap(int[] arr, int k) {
        // maxheap priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int val : arr)
            pq.add(val);
        while (k-- > 0)
            System.out.print(pq.remove() + " ");
    }

    // ! Most Optimised Approach - use minheap - O(n.lognK)
    // sare elements na insert karake, kuch element ko insert karake k largest
    // element nikalle
    // kisi bhi time par sirf 3 elements insert krenge
    // ek window kind rkhlenge "k" size ka, & new elemnt se check krate rhnge so
    // remove & insert
    public static void kLargestMinHeap(int[] arr, int k) {
        // min heap - bydefault - priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // first insert k elements
        for (int i = 0; i < k; i++)
            pq.add(arr[i]);
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();

        while (!pq.isEmpty())
            res.add(pq.remove());
        for (int i = res.size() - 1; i >= 0; i--)
            System.out.print(res.get(i) + " ");
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
        // kLargestMaxHeap(arr, k);
        kLargestMinHeap(arr, k);
    }
}
