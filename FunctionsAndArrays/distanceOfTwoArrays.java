import java.io.*;
import java.util.*;

public class Main {

    public static int[] getdiff(int[] arr1, int[] arr2, int[] res) {
        int borrow = 0, i1 = arr1.length - 1, i2 = arr2.length - 1, i3 = res.length - 1;
        while (i2 >= 0) {
            // dealing with -ve indexs
            int d1 = (i1 < 0) ? 0 : arr1[i1];
            int d2 = arr2[i2];
            int ans = 0;
            int tempdiff = d2 + borrow;
            if (tempdiff >= d1) {
                ans = tempdiff - d1;
                borrow = 0;
            } else {
                ans = tempdiff + 10 - d1;
                borrow = -1;
            }
            res[i3] = ans;
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
        int n3 = n2;
        int[] res = new int[n3];
        res = getdiff(arr1, arr2, res);
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