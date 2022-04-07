import java.util.*;
import java.io.*;

public class longestConsecutiveSequenceOfElements {

    // sorting & traverse - O(nlogn + n)
    public static void longestConsecutiveElementsBrute(int[] arr) {
        // sorting
        Arrays.sort(arr);
        int maxStart = arr[0];
        int maxCount = 1;
        int curr = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            while (arr[i] == arr[i - 1] + 1) {
                count++;
                i++;
            }
            if (maxCount < count) {
                maxStart = curr;
                maxCount = count;
            }
            count = 1;
            curr = arr[i];
        }
        while (maxCount-- > 0) {
            System.out.print(maxStart++ + " ");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(values[i]);
        longestConsecutiveElementsBrute(arr);
    }
}