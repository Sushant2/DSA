import java.util.*;
import java.io.*;

//LEETCODE : 349

public class getCommonElement {

    public static void getCommon(int[] arr1, int[] arr2) {
        HashMap<Integer, Boolean> hash = new HashMap<>();
        for (int i = 0; i < arr1.length; i++)
            hash.put(arr1[i], true);

        // System.out.println(hash);
        for (int i = 0; i < arr2.length; i++) {
            if (hash.containsKey(arr2[i]) == true && hash.get(arr2[i]) == true) {
                System.out.print(arr2[i] + " ");
                hash.put(arr2[i], false);
            }
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

        getCommon(arr1, arr2);
    }
}