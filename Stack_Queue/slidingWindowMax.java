import java.util.*;
import java.io.*;

public class slidingWindowMax {

    // ! brute force - O(n*k) time
    public static int[] windowMaxArrBrute(int[] arr, int k) {
        int[] res = new int[arr.length - k + 1];
        for (int i = 0; i < arr.length - k + 1; i++) {
            int currMax = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                currMax = Math.max(currMax, arr[j]);
            }
            res[i] = currMax;
        }
        return res;
    }

    // ! most optimised using deque - O(n) time & O(k) space
    public static int[] windowMaxArr(int[] arr, int k) {

        // resultant array
        int[] res = new int[arr.length - k + 1];
        int idx = 0; // for resultant array
        // deque - doubly ended queue to store indexes
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            // remove first - if dq front excluded from the window
            if (dq.size() > 0 && dq.getFirst() <= i - k) {
                dq.removeFirst();
            }

            // remove last - if rear is smaller than our current ele
            while (dq.size() > 0 && arr[dq.getLast()] <= arr[i]) {
                dq.removeLast();
            }

            // add last - we can be ans of our curr window or upcoming window
            dq.addLast(i);

            // add widnow maximum(dq.getFirst()) in res array - start adding after
            // processing first window
            if (i >= k - 1)
                res[idx++] = arr[dq.getFirst()];

        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        int window = Integer.parseInt(br.readLine());
        int[] res = windowMaxArrBrute(arr, window);
        System.out.println("Maximum of windows are: ");
        for (int x : res)
            System.out.print(x + " ");
    }
}