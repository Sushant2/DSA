import java.io.*;
import java.util.*;

//! Recursive relation : f(n) = f(n-1) + (n-1)*f(n-2) 

public class friendsPairing {

    // time compl : O(2^n) space : O(1) & recursion call stack space : O(n)
    public static int recursive(int n) {
        // when there is only 1 friend
        if (n <= 1)
            return 1;
        int solo = recursive(n - 1);
        int pair = recursive(n - 2) * (n - 1);
        return solo + pair;
    }

    public static int findPairingWays(int n) {
        return recursive(n);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int totalWays = findPairingWays(n);
        System.out.println(totalWays);
    }
}
