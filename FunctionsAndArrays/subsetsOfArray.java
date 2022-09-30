import java.io.*;
import java.util.*;

public class Main {
    public static int dtob(int n) {
        int ans = 0, pow = 1;
        while (n != 0) {
            ans += (n % 2) * pow;
            n /= 2;
            pow *= 10;
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        int ts = (int) Math.pow(2, n);
        for (int i = 0; i < ts; i++) {
            int binaryno = dtob(i);
            // now extract bits from each binary number
            int power = (int) Math.pow(10, n - 1);
            for (int b = 0; b < n; b++) { // extract n no. of bits from each binary number
                int bit = (binaryno / power) % 10;
                if (bit == 1) {
                    System.out.print(arr[b] + "\t");
                } else {
                    System.out.print("-\t");
                }
                power /= 10;
            }
            System.out.println();

        }
    }

}