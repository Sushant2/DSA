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

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int ans = fib(n);
        System.out.println(ans);
    }
}
