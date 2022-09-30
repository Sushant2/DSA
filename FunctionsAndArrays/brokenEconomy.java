import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int k = scn.nextInt();
        int low = 0, high = n - 1;
        int floor = 0, ceil = 0;
        while (low <= high) {
            // int mid = low + (high-low)/2;
            int mid = (low + high) / 2;
            if (arr[mid] < k) {
                low = mid + 1;
                floor = arr[mid];
            } else if (arr[mid] > k) {
                high = mid - 1;
                ceil = arr[mid];
            } else {
                floor = arr[mid];
                ceil = arr[mid];
            }
        }
        System.out.println(ceil);
        System.out.println(floor);
    }

}