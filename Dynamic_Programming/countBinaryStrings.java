import java.io.*;
import java.util.*;
//count all binary strings of length n w/o consecutive 0s

public class countBinaryStrings {
    public static int recursive(int n, int last) {
        // base case
        if (n == 0)
            return 0;
        if (n == 1) {
            if (last == 1)
                return 2;
            if (last == 0)
                return 1;
        }
        if (last == 1)
            return recursive(n - 1, 1) + recursive(n - 1, 0);
        else
            return recursive(n - 1, 1);

    }

    public static int countBStrings(int n) {
        // initially last digit taken as 1, cos it'll give 2 choices
        return recursive(n, 1);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int count = countBStrings(n);
        System.out.println(count);
    }
}
