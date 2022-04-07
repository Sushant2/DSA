import java.util.*;
import java.io.*;

public class longestConsecutiveSequenceOfElements {

    // ! BRUTE FORCE
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
                if (maxCount < count) {
                    maxCount = count;
                    maxStart = curr;
                }
            } else {
                count = 1;
                curr = arr[i + 1];
            }
        }
        while (maxCount-- > 0) {
            System.out.print(maxStart++ + " ");
        }
    }

    // ! Using HashMap - time comp - O(n) - cos finding in hashmap takes O(1) -
    // constant time
    public static void longestConsecutiveElementsOptimised(int[] arr) {
        // first traversal - fill hashmap with boolean values
        HashMap<Integer, Boolean> hash = new HashMap<>();
        for (int val : arr)
            hash.put(val, true);
        // second traversal - make false if there exist an element arr[i]-1 for arr[i]
        // in hashmap - cos in that case arr[i] can't be starting ele of any sequence
        for (int val : arr)
            if (hash.containsKey(val - 1))
                hash.put(val, false);
        // third traversal - only work for those who are true in hashmap
        int maxStartPoint = 0;
        int maxLen = 0;
        for (int val : arr) {
            if (hash.get(val) == true) {
                int tempLen = 1;
                int tempStartPoint = val;
                while (hash.containsKey(tempStartPoint + tempLen))
                    tempLen++;
                if (tempLen > maxLen) {
                    maxStartPoint = tempStartPoint;
                    maxLen = tempLen;
                }
            }
        }
        for (int i = 0; i < maxLen; i++)
            System.out.print(maxStartPoint + i + " ");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(values[i]);
        // longestConsecutiveElementsBrute(arr);
        longestConsecutiveElementsOptimised(arr);
    }
}