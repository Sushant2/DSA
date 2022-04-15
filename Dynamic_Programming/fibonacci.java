import java.io.*;
import java.util.*;

public class fibonacci {

    // normal fiboncci recursion methood to find fibonacci number
    // !poor way of finding fibonacci number
    public static int fib(int n) {

        if (n == 0 || n == 1)
            return n;
        int fibnm1 = fib(n - 1);
        int fibnm2 = fib(n - 2);
        int fibn = fibnm1 + fibnm2;
        return fibn;
    }

    // ! USING MEMOIZATION method - answers memorise krenge
    // like a question bank array banaenege jo repeat problems h unka anser store
    // kralenge & more no.of faltu calls se bachenge

    public int fibMemoized(int n, int[] qb) {
        if (n == 0 || n == 1)
            return n;

        // preorder m check krlenge already que ka answer exist to nhi krta na
        if (qb[n] != 0)
            return qb[n];

        int fibnm1 = fibMemoized(n - 1, qb);
        int fibnm2 = fibMemoized(n - 2, qb);
        int fibn = fibnm1 + fibnm2;

        // post order store krlenge ques n answer
        qb[n] = fibn;
        return fibn;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int ans = fib(n);
        System.out.println(ans);
    }
}
