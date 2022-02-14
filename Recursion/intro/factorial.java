import java.io.*;
import java.util.*;

public class factorial {
    public static int fact(int n) {
        // base case
        if (n == 1 || n == 0)
            return 1;
        // faith
        int smallFact = fact(n - 1);
        // meeting expec
        return smallFact * n;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println(fact(n));
        scn.close();
    }
}
