import java.util.*;
import java.io.*;

public class slidingWindowMax {

    public static int[] windowMaxArr(int[] arr, int k) {

        // resultant array
        int[] res = new int[arr.length - k + 1];
        // deque - doubly ended queue
        Deque<Integer> dq = new ArrayDeque<>();
        

        return null;
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
        int[] res = windowMaxArr(arr, window);
    }
}