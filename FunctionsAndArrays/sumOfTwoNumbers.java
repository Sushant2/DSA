import java.io.*;
import java.util.*;

public class Main {

    public static int[] getsum(int[] arr1, int[] arr2, int[] res) {
        int carry = 0, i1 = arr1.length - 1, i2 = arr2.length - 1, i3 = res.length - 1;
        while (i3 >= 0) {
            // dealing with -ve indexs
            int d1 = (i1 < 0) ? 0 : arr1[i1];
            int d2 = (i2 < 0) ? 0 : arr2[i2];
            int tempsum = carry + d1 + d2;
            carry = tempsum / 10;
            int rem = tempsum % 10;
            res[i3] = rem;
            i1--;
            i2--;
            i3--;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n1 = scn.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = scn.nextInt();
        }
        int n2 = scn.nextInt();
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = scn.nextInt();
        }
        int n3 = Math.max(n1, n2) + 1;
        int[] res = new int[n3];
        res = getsum(arr1, arr2, res);
        // dealing with leading zeroes
        int lead = 0;
        while (res[lead] == 0) {
            lead++;
        }
        // printing res array
        for (int i = lead; i < n3; i++)
            System.out.println(res[i]);
    }

}