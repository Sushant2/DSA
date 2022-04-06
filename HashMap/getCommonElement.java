import java.util.*;
import java.io.*;

//LEETCODE : 349

public class getCommonElement {

    // using hash table - time comp - O(n)
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

    // sorting arrays & using 2 pointers - timme comp - O(nlogn)

    public static void getCommon2(int[] arr1, int[] arr2) {
        // using set
        Set<Integer> set = new HashSet<>();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j])
                i++;
            else if (arr1[i] > arr2[j])
                j++;
            else {
                set.add(arr1[i]);
                i++;
                j++;
            }
        }
        for (Integer x : set)
            System.out.print(x);
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

        // getCommon(arr1, arr2);
        getCommon2(arr1, arr2);
    }
}