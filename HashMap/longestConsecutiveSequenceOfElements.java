import java.util.*;
import java.io.*;

public class longestConsecutiveSequenceOfElements {

    // sorting & traverse - O(nlogn + n)
    public static void longestConsecutiveElementsBrute(int[] arr) {
        if (arr.length == 0)
            return;
        // sorting
        Arrays.sort(arr);
        int maxStart = arr[0];
        int maxCount = 1;
        int curr = arr[0];
        int count = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            // skip duplicates
            if (arr[i] == arr[i + 1])
                continue;
            else if (arr[i] + 1 == arr[i + 1]) {
                count++;
                if (maxCount < count)
                    maxCount = count;
            } else {
                count = 1;
                curr = arr[i];
            }
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