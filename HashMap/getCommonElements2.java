import java.util.*;
import java.io.*;

public class getCommonElements2 {

    public static void getIntersection(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            if (hash.containsKey(arr1[i])) {
                int freq = hash.get(arr1[i]);
                hash.put(arr1[i], freq + 1);
            } else
                hash.put(arr1[i], 1);
        }
        for (int i = 0; i < arr2.length; i++) {
            
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr1 = new int[n];
        int m = Integer.parseInt(br.readLine());
        int[] arr2 = new int[m];
        String[] values1 = br.readLine().split(" ");
        String[] values2 = br.readLine().split(" ");

        for (int i = 0; i < n; i++)
            arr1[i] = Integer.parseInt(values1[i]);

        for (int i = 0; i < m; i++)
            arr2[i] = Integer.parseInt(values2[i]);

        getIntersection(arr1, arr2);
    }
}
