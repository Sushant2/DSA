import java.io.*;
import java.util.*;

public class powerLog {
    public static int powerlog(int x, int n) {
        // base case
        if (n == 0)
            return 1;
        // faith
        int smallPow = powerlog(x, n / 2);
        // meeting expec
        if (n % 2 == 0)
            return smallPow * smallPow;
        else
            return smallPow * smallPow * x;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int x = scn.nextInt();
        int n = scn.nextInt();
        System.out.println(powerlog(x, n));
        scn.close();
    }
}